/**
 * Turn on or off logging for the Component.
 * You can locate the Jetbrains log file from the menu Help | Show log in finder.
 */
package com.dsg.xsimsel;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

public class XSimulateSelectionLoggingAction  extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getProject();

        XSimulateSelectionComponent comp = project.getComponent(XSimulateSelectionComponent.class);
        if (comp != null)
            comp.toggleLogLevel();
    }

    @Override
    public void update(AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (e != null)
            e.getPresentation().setEnabledAndVisible(true);
    }
}
