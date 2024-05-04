package com.upl.nibss.hcmlib.utils;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by stanlee on 28/02/2018.
 */
@Service
public class GenerateCSVReport {

    public static void generateCSV(HttpServletResponse response, String reportHeader, List<String> reportContents) throws Exception {

        response.setHeader("Content-disposition", "attachment;filename=hcmreport.csv");
        List<String> rows = new ArrayList<>();
        rows.add(reportHeader);
        rows.add("\n");

        for (String content : reportContents) {
            rows.add(content);
            rows.add("\n");
        }

        Iterator<String> iter = rows.iterator();
        while (iter.hasNext()) {
            String outputString = (String) iter.next();
            response.getOutputStream().print(outputString);
        }

        response.getOutputStream().flush();
    }

}
