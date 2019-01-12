package me.arun.arunrxjavaexploring;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static me.arun.arunrxjavaexploring.FeatureCategory.FILE_DOWNLOAD;
import static me.arun.arunrxjavaexploring.FeatureCategory.FILE_UPLOAD;
import static me.arun.arunrxjavaexploring.FeatureCategory.GRAPH_QL;
import static me.arun.arunrxjavaexploring.FeatureCategory.SAMPLE_REQUEST;


/**
 * Created by Arun Pandian M on 27/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

@Retention(SOURCE)
@StringDef({SAMPLE_REQUEST,FILE_UPLOAD,FILE_DOWNLOAD,GRAPH_QL})
public @interface FeatureCategory
{
    String SAMPLE_REQUEST="SampleRequest";
    String FILE_UPLOAD="FileUpload";
    String FILE_DOWNLOAD="FileDownload";
    String GRAPH_QL="GraphQlRequest";
}
