/*
 * Copyright 2012-2020 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.couchbase.repository.query;

import org.springframework.data.couchbase.core.ReactiveCouchbaseOperations;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;

/**
 * @author Michael Nitschinger
 * @author Michael Reiche
 */
public class ReactiveCouchbaseRepositoryQuery implements RepositoryQuery {

	private final ReactiveCouchbaseOperations operations;
	private final CouchbaseQueryMethod queryMethod;
	private final NamedQueries namedQueries;

	public ReactiveCouchbaseRepositoryQuery(final ReactiveCouchbaseOperations operations,
			final CouchbaseQueryMethod queryMethod, final NamedQueries namedQueries) {
		this.operations = operations;
		this.queryMethod = queryMethod;
		this.namedQueries = namedQueries;
	}

	@Override
	public Object execute(final Object[] parameters) {
		return new ReactiveN1qlRepositoryQueryExecutor(operations, queryMethod, namedQueries).execute(parameters);
	}

	@Override
	public QueryMethod getQueryMethod() {
		return queryMethod;
	}

}