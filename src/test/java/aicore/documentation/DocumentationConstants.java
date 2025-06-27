package aicore.documentation;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentationConstants {
	public static Path IMAGE_FOLDER = Paths.get("img");
	public static Path PLATFORM_NAV_FOLDER = Paths.get(IMAGE_FOLDER.toString(), "PlatformNavigation");
	// App Library
	public static Path APP_LIBRARY_FOLDER = Paths.get(PLATFORM_NAV_FOLDER.toString(), "AppLibrary");
	public static Path CREATE_NEW_APP_IMAGE = Paths.get(APP_LIBRARY_FOLDER.toString(), "CreateNewApp.png");
	public static Path CREATE_NEW_APP_OPTIONS_IMAGE = Paths.get(APP_LIBRARY_FOLDER.toString(), "CreateNewApp.png");
	public static Path BROWSE_TEMPLATES_IMAGE = Paths.get(APP_LIBRARY_FOLDER.toString(), "BrowseTemplates.png");
	public static Path NEW_APP_POPUP_IMAGE = Paths.get(APP_LIBRARY_FOLDER.toString(), "NewAppPopup.png");
	public static Path PREVIEW_APP_IMAGE = Paths.get(APP_LIBRARY_FOLDER.toString(), "PreviewApp.png");

}
