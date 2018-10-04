# X11 Simulate Middle-Button Paste
**Plugin Name:** `x-sim-sel`

An IDEA plugin to simulate X11 paste of selected text, using the middle mouse button, without an intervening copy.

This plugin is not useful on systems running XWindow (e.g. linux).

The plugin watches for the user to select text within a given project and allows the user to paste that text to any editors in that project using the mouse-key configured for the `x-sim-sel` plugin.

### Load Plugin
Once built, deploy the file `./build/libs/xsimsel-{version}.jar` to a host running a Jetbrains IDE. 
Go to menu **Preferences... | Plugins | Install plugin from disk** and select the path to the jar plugin file.

### Configuration
You must explicitly use the menu **Preferences | Keymaps**, scroll down to x-sim-sel and right click to add a mouse shortcut. 
You may want to assign the shortcut to the middle mouse button by clicking it in the resulting dialog. 
Ignore the warning below that it may already be defined for other actions. After pressing **OK**, choose to remove the conflicting
shortcuts in the dialog that follows.

## Build
You have to use intellij to build it, but not with the `Build` menu, unless you are building it to use from a Run configuration (or Debug).

Open the Gradle tab on the far right of the IDE. Expand `Tasks/build` and double-click `build`.

Edit `./build.gradle` to set the version of the plugin.

### Run and Debug
You can run the plugin in an intellij sandbox using the intellij run configuration.

## Code
The _component_ element in `./src/main/resources/META-INF/plugin.xml` defines the text selection-change handler.
The _actions_ element in `./src/main/resources/META-INF/plugin.xml` defines the Edit menu item to be added to provide simulating X middle button
including key and button defaults for the action.

## References
* http://www.jetbrains.org/intellij/sdk/docs/welcome.html
* https://www.programcreek.com/java-api-examples/?api=com.intellij.openapi.command.CommandProcessor
* https://upsource.jetbrains.com/idea-ce/structure/idea-ce-d00d8b4ae3ed33097972b8a4286b336bf4ffcfab/platform

