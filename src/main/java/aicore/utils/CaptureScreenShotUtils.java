package aicore.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;

import aicore.framework.ConfigUtils;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;

public class CaptureScreenShotUtils {

    public static String version;
    private static final String VERSION_XPATH = "//*[@data-testid=\"LogoutIcon\"]/../../../../following-sibling::li//div//span[1]";

    public static String versionCapture(Page page) {
        String appVersion = page.locator(VERSION_XPATH).textContent();

        System.out.println("Captured version: " + appVersion);
        CaptureScreenShotUtils.version = appVersion;
        page.keyboard().press("Escape");
        HomePageUtils.closeMainMenu(page);
        return appVersion;
    }

    public static void captureScreenshot(Page page, Locator button, Path path) throws IOException {
        page.waitForTimeout(1000);
        Path screenshotPath = Paths.get(path.toString());
        button.scrollIntoViewIfNeeded();

        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));
        String screenshotPathStr = screenshotPath.toString();
        if (button.isVisible()) {
            button.scrollIntoViewIfNeeded();
            BoundingBox box = button.boundingBox();
            if (box != null) {
                BufferedImage image = ImageIO.read(new File(screenshotPathStr));
                int padding = 10;
                Graphics2D graphics = image.createGraphics();
                graphics.setColor(Color.RED);
                graphics.setStroke(new BasicStroke(4));
                graphics.drawRect((int) box.x - padding, (int) box.y - padding, (int) box.width + (2 * padding),
                        (int) box.height + (2 * padding));
                graphics.dispose();
                ImageIO.write(image, "png", new File(screenshotPathStr));
            }
        }
    }

    public static void compareAndStoreResultsIfReady(Page page, String subFolder) throws IOException, Exception {

        File benchmark = new File("img/PlatformNavigation/" + "Version" + version, subFolder);
        File results = new File("img/PlatformNavigation/Results", subFolder);
        File previousBenchmark = new File("img/PlatformNavigation/" + "Version" + ConfigUtils.getValue("current_version"), subFolder);

        File dir1 = benchmark;
        File dir2 = previousBenchmark;
        File diffDir = results;

        if (!dir1.exists() || !dir2.exists()) {
            return;
        }
        if (!diffDir.exists()) {
            diffDir.mkdirs();
        }

        File[] files1 = dir1.listFiles((d, name) -> name.endsWith(".png") || name.endsWith(".jpg"));
        File[] files2 = dir2.listFiles((d, name) -> name.endsWith(".png") || name.endsWith(".jpg"));

        if (files1 == null || files2 == null) {
            throw new IllegalArgumentException("Failed to list files in one or both subfolders.");
        }

        List<String> changedfiles = new ArrayList<>();
        List<String> Unchangedfiles = new ArrayList<>();

        for (File f1 : files1) {
            for (File f2 : files2) {
                if (f1.getName().equals(f2.getName())) {
                    String diffPath = new File(results, f1.getName()).getAbsolutePath();
                    boolean match = compareImages(f1.getAbsolutePath(), f2.getAbsolutePath(), diffPath);

                    if (!match) {
                        changedfiles.add("Image: " + f1.getName());
                    } else {
                        Unchangedfiles.add("Image: " + f1.getName());
                    }
                }
            }
        }

        writeResultFile(new File(diffDir, "changedfiles.txt"), changedfiles);
        writeResultFile(new File(diffDir, "Unchangedfiles.txt"), Unchangedfiles);

        cleanOldVersionDirectories("img/PlatformNavigation", "Version" + ConfigUtils.getValue("current_version"), "Version" + version);
    }

    public static boolean compareImages(String actualImagePath, String expectedImagePath, String diffImagePath)
            throws Exception {
        File actualFile = new File(actualImagePath);
        File expectedFile = new File(expectedImagePath);
        File diffFile = new File(diffImagePath);
        // If expected image doesn't exist, create it from actual
        if (!expectedFile.exists()) {
            Files.copy(actualFile.toPath(), expectedFile.toPath());
            return true;
        }
        BufferedImage expected = ImageIO.read(expectedFile);
        BufferedImage actual = ImageIO.read(actualFile);
        // Resize actual image if dimensions mismatch
        if (expected.getWidth() != actual.getWidth() || expected.getHeight() != actual.getHeight()) {
            actual = resizeImage(actual, expected.getWidth(), expected.getHeight());
        }
        // Perform image comparison
        ImageComparison imageComparison = new ImageComparison(expected, actual);
        imageComparison.setDifferenceRectangleColor(Color.GREEN); 
        imageComparison.setDestination(diffFile);
        ImageComparisonResult result = imageComparison.compareImages();
        return result.getDifferencePercent() == 0.0;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, targetWidth, targetHeight);
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }

    private static void writeResultFile(File file, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private static void deleteDirectory(File dir) {
        if (dir.exists()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
            dir.delete();
        }
    }

    public static void cleanOldVersionDirectories(String parentDir, String prevVersion, String currVersion) {
        File parent = new File(parentDir);
        File[] dirs = parent.listFiles(File::isDirectory);
        System.out.println("Cleaning old version directories in: " + Arrays.toString(dirs));
        if (dirs != null) {
            for (File dir : dirs) {
                String dirName = dir.getName();
                System.out.println("Checking directory: " + dirName);
                System.out.println("Current version: " + currVersion);
                System.out.println("Previous version: " + prevVersion);
                if (!dirName.equals(prevVersion) && !dirName.equals(currVersion) && !dirName.equals("Results")) {
                    deleteDirectory(dir);
                    System.out.println("Deleted: " + dir.getAbsolutePath());
                }
            }
        }
    }

    public static void captureScreenshot(Page page, Path path) {
        page.waitForTimeout(1000);
        Path screenshotPath = Paths.get(path.toString());
        page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath));

    }

}
