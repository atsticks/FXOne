package org.fxone.ui.rt.components.dialog;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Window;

import javax.inject.Singleton;

import org.fxone.ui.rt.components.dialog.Dialog.DialogBuilder;

/**
 *
 * @author Anton Smirnov (dev@antonsmirnov.name)
 */
@Singleton
public final class DialogManagerImpl implements FXDialogManager{
    
	private DialogManagerImpl() {
		// Singleton
	}
	
    /**
     * Show information dialog box as parentWindow child
     * 
     * @param title dialog title
     * @param message dialog message
     * @param owner parent window
     */
    public void showInfo(String title, String message, Window owner) {
        new DialogBuilder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setInfoIcon()
            .setMessage(message)            
            .addOkButton()
                .build()
                    .show();
    }
    
    
    /**
     * Show information dialog box as parentStage child
     * 
     * @param title dialog title
     * @param message dialog message
     */
    public void showInfo(String title, String message) {
        showInfo(title, message, null);
    }
    
    /**
     * Show information dialog box as parentStage child
     * 
     * @param title dialog title
     * @param message dialog message
     */
    public void showYesNo(String title, String message, javafx.event.EventHandler<ActionEvent> h) {
    	showYesNo(title, message, null, h);
    }
    
    /**
     * Show information dialog box as parentWindow child
     * 
     * @param title dialog title
     * @param message dialog message
     * @param owner parent window
     */
    public void showYesNo(String title, String message, Window owner, javafx.event.EventHandler<ActionEvent> h) {
        new DialogBuilder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setInfoIcon()
            .setMessage(message)            
            .addYesButton(h)
            .addNoButton(null)
                .build()
                    .show();
    }

    /**
     * Show warning dialog box as parentStage child
     * 
     * @param title dialog title
     * @param message dialog message
     * @param owner parent window
     */
    public void showWarning(String title, String message, Window owner) {
        new DialogBuilder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setWarningIcon()
            .setMessage(message)
            .addOkButton()
                .build()
                    .show();
    }
    
    /**
     * Show warning dialog box
     * 
     * @param title dialog title
     * @param message dialog message
     */
    public void showWarning(String title, String message) {
        showWarning(title, message, null);
    }
    
    /**
     * Show error dialog box
     * 
     * @param title dialog title
     * @param message dialog message
     * @param owner parent window
     */
    public DialogBuilder buildDialog(String title, Node content, Window owner) {
        return new DialogBuilder()
            .create(content)
            .setOwner(owner)
            .setTitle(title);
    }
    
    /**
     * Show error dialog box
     * 
     * @param title dialog title
     * @param message dialog message
     */
    public DialogBuilder buildDialog(String title, Node content) {
    	return buildDialog(title, content, null);
    }

    /**
     * Show error dialog box
     * 
     * @param title dialog title
     * @param message dialog message
     * @param owner parent window
     */
    public void showError(String title, String message, Window owner) {
        new DialogBuilder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setErrorIcon()
            .setMessage(message)
            .addOkButton()
                .build()
                    .show();
    }
    
    /**
     * Show error dialog box
     * 
     * @param title dialog title
     * @param message dialog message
     */
    public void showError(String title, String message) {
        showError(title, message, null);
    }
    
    /**
     * Show error dialog box with stacktrace
     * 
     * @param title dialog title
     * @param message dialog message
     * @param t throwable
     * @param owner parent window 
     */
    public void showThrowable(String title, String message, Throwable t, Window owner) {
        new DialogBuilder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setThrowableIcon()
            .setMessage(message)
            .setStackTrace(t)
            .addOkButton()
                .build()
                    .show();
    }
    
    /**
     * Show error dialog box with stacktrace
     * 
     * @param title dialog title     
     * @param message dialog message
     * @param t throwable
     */
    public void showThrowable(String title, String message, Throwable t) {
        showThrowable(title, message, t, null);
    }
    
    /**
     * Build confirmation dialog builder
     * 
     * @param title dialog title     
     * @param message dialog message
     * @param owner parent window
     * @return 
     */
    public DialogBuilder buildConfirmation(String title, String message, Window owner) {
        return new DialogBuilder()
            .create()
            .setOwner(owner)
            .setTitle(title)
            .setConfirmationIcon()
            .setMessage(message);
    }
    
    /**
     * Build confirmation dialog builder
     * 
     * @param title dialog title     
     * @param message dialog message
     * @return 
     */
    public DialogBuilder buildConfirmation(String title, String message) {
        return buildConfirmation(title, message, null);
    }

    /**
     * 
     */
	@Override
	public void openDialog(Dialog dialog) {
		throw new UnsupportedOperationException();
	}

}

