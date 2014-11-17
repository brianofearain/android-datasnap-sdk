package com.datasnap.android.db;

import com.datasnap.android.models.BasePayload;

public interface IPayloadSerializer {
  String serialize(BasePayload payload);

  BasePayload deserialize(String str);
}
