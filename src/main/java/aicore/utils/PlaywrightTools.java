import com.microsoft.playwright.Page; // From Playwright
import com.microsoft.playwright.options.AriaRole; // For Button Roles
import dev.langchain4j.agent.tool.Tool; // From LangChain4j



public class PlaywrightTools {

    @Tool(name = "ClickButton", description = "Clicks a button on the webpage based on its aria-label or text content.")
    public void clickButton(Page page, String buttonIdentifier) {
        // Try to find the button by aria-label first
        var button = page.locator("button[aria-label='" + buttonIdentifier + "']");
        if (button.count() > 0) {
            button.first().click();
            return;
        }

        // If not found, try to find the button by its text content
        button = page.locator("button:has-text('" + buttonIdentifier + "')");
        if (button.count() > 0) {
            button.first().click();
            return;
        }

        throw new RuntimeException("Button with identifier '" + buttonIdentifier + "' not found.");
    }
}