package com.jamie.idea.plugin;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.MessageType;

public class FirstPluginAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        NotificationGroup fristPluginId = new NotificationGroup("fristPluginId", NotificationDisplayType.BALLOON, true);
        Notification notification = fristPluginId.createNotification("点击测试", MessageType.INFO);
        Notifications.Bus.notify(notification);

    }

}
