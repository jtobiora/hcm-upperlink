package com.upl.nibss.hcmlib.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Created by stanlee on 19/01/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailContent {

    private String from;
    private String[] to;
    private String subject;
    private List<Object> attachments;
    private Map<String, Object> model;

}
