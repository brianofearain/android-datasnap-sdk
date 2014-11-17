
package com.datasnap.android.errors;

public class InvalidSettingsException extends Exception {

  private static final long serialVersionUID = 8805799353531572356L;

  private String setting;

  public InvalidSettingsException(String setting) {
    super(String.format("Provider requires the setting '%s'.", setting));
    this.setting = setting;
  }

  public InvalidSettingsException(String setting, String message) {
    super(String.format("Provider requires the setting '%s': %s", setting, message));
    this.setting = setting;
  }

  public String getSetting() {
    return setting;
  }
}
