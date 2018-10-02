package com.dsg.xsimsel;

import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.event.SelectionListener;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.diagnostic.Logger;

import java.util.Objects;

public class XSimulateSelectionComponent extends AbstractProjectComponent {
    private static final Logger log = Logger.getInstance(XSimulateSelectionAction.class);

    protected XSimulateSelectionComponent(Project project) {
        super(project);
    }

    public void initComponent() {
        log.info("component inited");
    }

    public void disposeComponent() {
    }

    @Override
    public void projectOpened() {
        MessageBusConnection connection = myProject.getMessageBus().connect(myProject);
        connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
            @Override
            public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                // On each file, add a listener of text selections
                log.debug("file opened");
                SelectionListener sl = event -> {
                    // NOTE: This is text selection
                    String selText = event.getEditor().getSelectionModel().getSelectedText();
                    // ignore removal of selection when the middle mouse button repositions the cursor
                    log.debug("selection changed to " + selText);
                    if(selText != null) {
                        currentSelection = selText;
                        log.debug("currentSelection is " + currentSelection);
                    }
                };
                SelectionModel model = source.getSelectedTextEditor().getSelectionModel();
                log.debug("adding listener model = " + model.toString());
                model.addSelectionListener(sl);
            }
            @Override
            public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
            }
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                // NOTE: This is NOT text selection in an editor!
            }
        });
    }

    String getCurrentSelection() {
        return currentSelection;
    }

    Boolean hasSelectionText() { return currentSelection!=null; }

    private String currentSelection=null;

}
