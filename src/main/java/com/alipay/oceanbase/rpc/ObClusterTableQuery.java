/*-
 * #%L
 * OBKV Table Client Framework
 * %%
 * Copyright (C) 2021 OceanBase
 * %%
 * OBKV Table Client Framework is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *          http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 * #L%
 */

package com.alipay.oceanbase.rpc;

import com.alipay.oceanbase.rpc.location.model.partition.ObPair;
import com.alipay.oceanbase.rpc.protocol.payload.impl.execute.ObTableEntityType;
import com.alipay.oceanbase.rpc.protocol.payload.impl.execute.query.ObHTableFilter;
import com.alipay.oceanbase.rpc.protocol.payload.impl.execute.query.ObTableQuery;
import com.alipay.oceanbase.rpc.stream.ObTableClientQueryStreamResult;
import com.alipay.oceanbase.rpc.stream.QueryResultSet;
import com.alipay.oceanbase.rpc.table.AbstractTableQuery;
import com.alipay.oceanbase.rpc.table.ObTable;
import com.alipay.oceanbase.rpc.table.ObTableClientQueryImpl;
import com.alipay.oceanbase.rpc.table.api.TableQuery;

public class ObClusterTableQuery extends AbstractTableQuery {

    private final ObTableClientQueryImpl tableClientQuery;

    ObClusterTableQuery(ObTableClientQueryImpl tableQuery) {
        this.tableClientQuery = tableQuery;
    }

    /*
     * Get table name.
     */
    @Override
    public String getTableName() {
        return tableClientQuery.getTableName();
    }

    /*
     * Get ob table query.
     */
    @Override
    public ObTableQuery getObTableQuery() {
        return tableClientQuery.getObTableQuery();
    }

    /*
     * Execute.
     */
    @Override
    public QueryResultSet execute() throws Exception {
        return tableClientQuery.execute();
    }

    @Override
    public QueryResultSet executeInit(ObPair<Long, ObTable> entry) throws Exception {
        throw new IllegalArgumentException("not support executeInit");
    }

    @Override
    public QueryResultSet executeNext(ObPair<Long, ObTable> entry) throws Exception {
        throw new IllegalArgumentException("not support executeNext");
    }

    /*
     * Execute internal.
     */
    public ObTableClientQueryStreamResult executeInternal() throws Exception {
        return tableClientQuery.executeInternal();
    }

    /*
     * Select.
     */
    @Override
    public TableQuery select(String... columns) {
        tableClientQuery.select(columns);
        return this;
    }

    /*
     * 只有 limit query 需要，其他不需要
     * @param keys
     * @return
     */
    @Override
    public TableQuery setKeys(String... keys) {
        throw new IllegalArgumentException("Not needed");
    }

    /*
     * Limit.
     */
    @Override
    public TableQuery limit(int offset, int limit) {
        tableClientQuery.limit(offset, limit);
        return this;
    }

    /**
     * Add scan range.
     */
    @Override
    public TableQuery addScanRange(Object[] start, boolean startEquals, Object[] end,
                                   boolean endEquals) {
        tableClientQuery.addScanRange(start, startEquals, end, endEquals);
        return this;
    }

    /**
     * Add scan range starts with.
     */
    @Override
    public TableQuery addScanRangeStartsWith(Object[] start, boolean startEquals) {
        tableClientQuery.addScanRangeStartsWith(start, startEquals);
        return this;
    }

    /**
     * Add scan range ends with.
     */
    @Override
    public TableQuery addScanRangeEndsWith(Object[] end, boolean endEquals) {
        tableClientQuery.addScanRangeEndsWith(end, endEquals);
        return this;
    }

    /**
     * Scan order.
     */
    @Override
    public TableQuery scanOrder(boolean forward) {
        tableClientQuery.scanOrder(forward);
        return this;
    }

    /**
     * Index name.
     */
    @Override
    public TableQuery indexName(String indexName) {
        tableClientQuery.indexName(indexName);
        return this;
    }

    /**
     * Filter string.
     */
    @Override
    public TableQuery filterString(String filterString) {
        tableClientQuery.filterString(filterString);
        return this;
    }

    /**
     * Set h table filter.
     */
    @Override
    public TableQuery setHTableFilter(ObHTableFilter obHTableFilter) {
        return tableClientQuery.setHTableFilter(obHTableFilter);
    }

    /**
     * Set batch size.
     */
    @Override
    public TableQuery setBatchSize(int batchSize) {
        return tableClientQuery.setBatchSize(batchSize);
    }

    @Override
    public TableQuery setMaxResultSize(long maxResultSize) {
        return tableClientQuery.setMaxResultSize(maxResultSize);
    }

    /**
     * Clear.
     */
    @Override
    public void clear() {
        tableClientQuery.clear();
    }

    /**
     * Set entity type.
     */
    @Override
    public void setEntityType(ObTableEntityType entityType) {
        super.setEntityType(entityType);
        tableClientQuery.setEntityType(entityType);
    }
}
