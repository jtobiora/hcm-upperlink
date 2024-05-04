package com.upl.nibss.hcmlib.embeddables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by stanlee on 22/01/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PensionInfo {
    private String pfa;
    private String rsa;
}
