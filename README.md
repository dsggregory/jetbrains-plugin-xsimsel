# X11 Simulate Middle-Button Paste
**Plugin Name:** `x-sim-sel`

An IDEA plugin to simulate X11 paste of selected text, using the middle mouse button, without an intervening copy.

This plugin is not useful on systems running XWindow (e.g. linux).

The plugin watches for the user to select text within a given project and allows the user to paste that text to any editors in that project using the mouse-key configured for the `x-sim-sel` plugin.

### Load Plugin
Once built, deploy the file `./build/libs/xsimsel-{version}.jar` to a host running a Jetbrains IDE. Go to menu **Preferences... | Plugins | Install plugin from disk** and select the path to the jar plugin file.

### Configuration
You must explicitly use the menu **Preferences | Keymaps**, scroll down to x-sim-sel and right click to add a mouse shortcut. You may want to assign the shortcut to the middle mouse button by clicking it in the resulting dialog. Ignore the warning that it may already be defined for other actions. You may leave the other actions undisturbed.

## Build
You have to use intellij to build it. Just import the top directory and watch it go. May take some time to initially load all deps (gradle, et.al.)

Edit `./build.gradle` to set the version of the plugin.

### Run and Debug
You can run the plugin in an intellij sandbox using the intellij run configuration.

## Code
The _component_ element in `./src/main/resources/META-INF/plugin.xml` defines the test-selection change handler.
The _actions_ element in `./src/main/resources/META-INF/plugin.xml` defines the Edit menu item to be added to provide simulating X middle button
including key and button defaults for the action.

## References
* http://www.jetbrains.org/intellij/sdk/docs/welcome.html
* https://www.programcreek.com/java-api-examples/?api=com.intellij.openapi.command.CommandProcessor