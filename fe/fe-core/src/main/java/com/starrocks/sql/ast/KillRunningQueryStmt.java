// Copyright 2021-present StarRocks, Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


package com.starrocks.sql.ast;

import com.starrocks.analysis.RedirectStatus;
import com.starrocks.sql.parser.NodePosition;

/**
 * Representation of a Kill running query statement.
 * Acceptable syntax:
 * KILL RUNNING QUERY 'custom_query_id'
 */
public class KillRunningQueryStmt extends StatementBase {
    private final String customQueryId;

    public KillRunningQueryStmt(String customQueryId) {
        this(customQueryId, NodePosition.ZERO);
    }

    public KillRunningQueryStmt(String customQueryId, NodePosition pos) {
        super(pos);
        this.customQueryId = customQueryId;
    }

    public String getCustomQueryId() {
        return customQueryId;
    }

    @Override
    public RedirectStatus getRedirectStatus() {
        return RedirectStatus.NO_FORWARD;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitKillRunningQueryStatement(this, context);
    }
}

