package com.dsg.xsimsel;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.diagnostic.Logger;

import static com.intellij.openapi.application.ApplicationManager.getApplication;

public class XSimulateSelectionAction extends AnAction {

    private static final Logger log = Logger.getInstance(XSimulateSelectionAction.class);

    private void insertToEditor(Project project, Editor editor, String str) {
        CommandProcessor.getInstance().executeCommand(project, () -> getApplication().runWriteAction(() -> {
            if (editor != null) {
                int offset = editor.getCaretModel().getOffset();
                Document document = editor.getDocument();
                if (str != null) {
                    document.insertString(offset, str);
                    editor.getCaretModel().moveToOffset(offset + str.length());
                }
            }
        }), "InsertResultToEditor", "", UndoConfirmationPolicy.DO_NOT_REQUEST_CONFIRMATION);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);

        XSimulateSelectionComponent comp = project.getComponent(XSimulateSelectionComponent.class);
        log.debug("actionPerformed has sel = " + comp.hasSelectionText());
        if(comp!=null && comp.hasSelectionText())
            insertToEditor(project, editor, comp.getCurrentSelection());
    }

    @Override
    public void update(AnActionEvent e) {
        // Set the availability only when some text is selected
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        Boolean pe = (project != null && editor != null);
        // we could check that there has been a selection, but 99.9999% of the time, there will have been.
        e.getPresentation().setEnabledAndVisible(pe);
        log.debug("vis: pe=" + pe + "\n");
    }
}
