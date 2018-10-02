package com.dsg.xsimsel;

import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.editor.event.SelectionListener;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class XSimulateSelectionComponent extends AbstractProjectComponent {

    protected XSimulateSelectionComponent(Project project) {
        super(project);
    }

    public void initComponent() {
        //System.out.println("component inited");
    }

    public void disposeComponent() {
        // TODO
    }

    @Override
    public void projectOpened() {
        MessageBusConnection connection = myProject.getMessageBus().connect(myProject);
        connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
            @Override
            public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                // On each file, add a listener of text selections
                SelectionListener sl = event -> {
                    // NOTE: This is text selection
                    String selText = event.getEditor().getSelectionModel().getSelectedText();
                    // ignore removal of selection when the middle mouse button repositions the cursor
                    if(selText != null) {
                        currentSelection = selText;
                        System.out.println("selection changed to " + currentSelection);
                    }
                };
                Objects.requireNonNull(source.getSelectedTextEditor()).getSelectionModel().addSelectionListener(sl);
            }
            @Override
            public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                //System.out.println("In filemanager closed");
            }
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                // NOTE: This is NOT text selection in an editor!
                //System.out.println("In filemanager selectionChanged");
            }
        });
    }

    String getCurrentSelection() {
        return currentSelection;
    }

    Boolean hasSelectionText() { return currentSelection!=null; }

    private String currentSelection=null;

}
