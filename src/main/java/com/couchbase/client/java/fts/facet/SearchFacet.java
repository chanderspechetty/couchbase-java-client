/*
 * Copyright (c) 2016 Couchbase, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.couchbase.client.java.fts.facet;


import com.couchbase.client.core.annotations.InterfaceAudience;
import com.couchbase.client.core.annotations.InterfaceStability;
import com.couchbase.client.java.document.json.JsonObject;

/**
 * Base class for all FTS facets in querying.
 *
 * @author Simon Baslé
 * @author Michael Nitschinger
 * @since 2.3.0
 */
@InterfaceStability.Experimental
@InterfaceAudience.Public
public abstract class SearchFacet {

    private final String name;
    private final String field;
    private final int limit;

    protected SearchFacet(String name, String field, int limit) {
        this.name = name;
        this.field = field;
        this.limit = limit;
    }

    public static TermFacet term(String name, String field, int limit) {
        return new TermFacet(name, field, limit);
    }

    public static NumericRangeFacet numeric(String name, String field, int limit) {
        return new NumericRangeFacet(name, field, limit);
    }

    public static DateRangeFacet date(String name, String field, int limit) {
        return new DateRangeFacet(name, field, limit);
    }

    public String name() {
        return name;
    }

    public void injectParams(JsonObject queryJson) {
        queryJson.put("size", limit);
        queryJson.put("field", field);
    }

}
