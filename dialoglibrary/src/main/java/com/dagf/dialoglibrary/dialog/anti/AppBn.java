package com.dagf.dialoglibrary.dialog.anti;

import android.content.Context;
import android.content.pm.PackageManager;

public class AppBn {
private String name;
private String url_to;
private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl_to() {
        return url_to;
    }

    public void setUrl_to(String url_to) {
        this.url_to = url_to;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    private String ban;

    public boolean isBanned(Context context) throws PackageManager.NameNotFoundException {
        String version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;

        return ban != null && ban.equalsIgnoreCase("0") && getVersion() != null && getVersion().equals(version);
    }
}
