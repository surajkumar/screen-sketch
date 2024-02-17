package io.github.surajkumar.screen;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;

public class NativeFrameAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(NativeFrameAction.class);
    private final JFrame frame;
    private int previousStyle;

    public NativeFrameAction(JFrame frame) {
        this.frame = frame;
    }

    public void allowPassThrough() {
        if (!frame.isVisible()) {
            throw new RuntimeException(
                    "JFrame must be set to visible for this to allow pass through");
        }
        LOGGER.info("Allowing pass through, through JFrame");
        WinDef.HWND hwnd = new WinDef.HWND(Native.getComponentPointer(frame));
        previousStyle = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
        User32.INSTANCE.SetWindowLong(
                hwnd,
                WinUser.GWL_EXSTYLE,
                (previousStyle | WinUser.WS_EX_LAYERED | WinUser.WS_EX_TRANSPARENT));
    }

    public void disablePassThrough() {
        LOGGER.info("Blocking pass through, through JFrame");
        WinDef.HWND hwnd = new WinDef.HWND(Native.getComponentPointer(frame));
        User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, previousStyle);
    }
}
