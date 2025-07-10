package aicore.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class TerminalPageUtils {

    private static final String OUTPUT_XPATH = "(//div[contains(@class, 'ace_layer')]//div[contains(@class, 'ace_line')][last()-2]//span[contains(@class, '{type}')])";
    private static final String LOADING_SCREEN_XPATH = "//h5[contains(text(),'Loading')]";
    private static final String FILESIZE_XPATH = "//div[contains(@class, 'ace_layer')]//div[contains(@class, 'ace_line')][last()-2]//span[contains(@class,'ace_constant')]";
    private static final String FILENAME_XPATH = "//div[contains(@class, 'ace_layer')]//div[contains(@class, 'ace_line')][last()-2]//span[contains(@class,'ace_string')][2]";
    private static final String FILEDATE_XPATH = "//div[contains(@class, 'ace_layer')]//div[contains(@class, 'ace_line')][last()-2]//span[contains(@class,'ace_string')][last()]";

    public static void runCommand(Page page, String pixelCommand) {
        page.getByRole(AriaRole.TEXTBOX).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        if (pixelCommand.contains("{VECTOR_ID}")) {
            String copiedId = (String) page.evaluate("()=>navigator.clipboard.readText()");
            pixelCommand = pixelCommand.replace("{VECTOR_ID}", copiedId);
        }
        page.getByRole(AriaRole.TEXTBOX).fill(pixelCommand);
        page.keyboard().press("Enter");
    }

    public static void verifyUserIsOnTerminalPage(Page page) {
        String currentUrl = page.url();
        if (!currentUrl.contains("embed-terminal")) {
            throw new IllegalStateException("Not on the Terminal page. Current URL: " + currentUrl);
        }
    }

    public static String getActualPixelOutput(Page page, String language) {
        String actualOutput = switch (language) {
            case "Pixel" -> {
                page.locator(OUTPUT_XPATH.replace("{type}", "ace_string"))
                        .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                yield page.locator(OUTPUT_XPATH.replace("{type}", "ace_string"))
                        .textContent().trim().replace("\"", "");
            }
            case "Python" -> {
                page.locator(OUTPUT_XPATH.replace("{type}", "ace_constant"))
                        .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
                yield page.locator(OUTPUT_XPATH.replace("{type}", "ace_constant"))
                        .textContent().trim();
            }
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
        return actualOutput;
    }

    public static void waitForLoadingScreennToDisappear(Page page) {
        page.locator(LOADING_SCREEN_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    public static double getActualFileSize(Page page) {
        page.locator(FILESIZE_XPATH).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        String fileSize = page.locator(FILESIZE_XPATH).textContent();
        double sizeOfFile = Double.parseDouble(fileSize);
        int actualFileSize = (int) Math.round(sizeOfFile);
        return actualFileSize;
    }

    public static String getActualFileName(Page page) {
        page.locator(FILENAME_XPATH).isVisible();
        String fileName = page.locator(FILENAME_XPATH).textContent();
        String nameOfFile = fileName.replace("\"", "");
        return nameOfFile;
    }

    public static String getActualFileDate(Page page) {
        page.locator(FILEDATE_XPATH).isVisible();
        String fileDate = page.locator(FILEDATE_XPATH).textContent();
        String dateOfFile = fileDate.replace("\"", "");
        String[] actualFileDate = CommonUtils.splitStringBySpace(dateOfFile, 0);
        return actualFileDate[0];
    }

    public static void changeToLanguage(Page page, String language) {
        // Handle intermittent popup with "Accept" CTAs if present
        boolean cookiePopupFlag = page.locator(".cookie-banner").isVisible();
        Locator popupAccept = page.locator("button:has-text('Accept')");
        if (cookiePopupFlag) {
            popupAccept.click(new Locator.ClickOptions().setTimeout(2000));
        }
        // Change the language by clicking on the language logo
        try {
            page.getByTitle(language)
                    .click(new Locator.ClickOptions().setTimeout(2000));
        } catch (Exception e) {
            throw new RuntimeException("Failed to change language to: " + language, e);
        }
    }
}
