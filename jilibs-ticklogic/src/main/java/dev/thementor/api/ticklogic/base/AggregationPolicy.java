/*
 * Copyright (c) 2025 Alireza Khodakarami
 *
 * Licensed under the MIT, (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://opensource.org/license/mit
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.thementor.api.ticklogic.base;

/**
 * Defines policies for aggregating results of multiple tick logic operations.
 */
public enum AggregationPolicy
{
    /**
     * Succeeds if any individual operation succeeds. Fails otherwise.
     */
    SUCCEED_ON_ANY_SUCCESS,
    /**
     * Fails if any individual operation fails. Succeeds otherwise.
     */
    FAIL_ON_ANY_FAILURE,
    /**
     * Succeeds only if all individual operations succeed. Fails otherwise.
     */
    SUCCEED_IF_ALL_SUCCESS,
    /**
     * Fails only if all individual operations fail. Succeeds otherwise.
     */
    FAIL_IF_ALL_FAILURE
}