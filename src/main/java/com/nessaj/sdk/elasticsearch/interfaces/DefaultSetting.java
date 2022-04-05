package com.nessaj.sdk.elasticsearch.interfaces;

import org.elasticsearch.common.settings.Settings;


/**
 * @author keming
 * @Date 2022/03/23 22:15
 */
public class DefaultSetting implements IndexSetting {
    @Override
    public Settings getSetting() {
         return Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1).build();

    }
}
