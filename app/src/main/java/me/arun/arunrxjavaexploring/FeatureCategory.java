package me.arun.arunrxjavaexploring;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.SOURCE;
import static me.arun.arunrxjavaexploring.FeatureCategory.CHAINING;
import static me.arun.arunrxjavaexploring.FeatureCategory.NETWORK;
import static me.arun.arunrxjavaexploring.FeatureCategory.OPERATOR;
import static me.arun.arunrxjavaexploring.FeatureCategory.PAGINATION;
import static me.arun.arunrxjavaexploring.FeatureCategory.ROOMDB;
import static me.arun.arunrxjavaexploring.FeatureCategory.RXBUS;
import static me.arun.arunrxjavaexploring.FeatureCategory.SAMPLE;

/**
 * Created by Arun Pandian M on 27/December/2018
 * arunsachin222@gmail.com
 * Chennai
 */

@Retention(SOURCE)
@StringDef({ROOMDB,NETWORK,CHAINING,PAGINATION,OPERATOR,RXBUS,SAMPLE})
public @interface FeatureCategory
{
    String ROOMDB="RxJava in Db";
    String NETWORK="RxJava operators and transformation";
    String SAMPLE="RxJava Sample";
    String CHAINING="RxJava chaining API Call";
    String PAGINATION="Pagination in RxJava";
    String OPERATOR="RxJava Operators";
    String RXBUS="Rx Bus";
}
