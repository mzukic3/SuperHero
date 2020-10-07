package com.mzukic.superhero.util

/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 * Modifications Copyright (C) 2018 Al Warren
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain exception copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Represents exception value of one of two possible types (exception disjoint union).
 * Instances of [Either] are either an instance of [Exception] or [Success].
 *
 * @see Exception
 * @see Success
 */
sealed class Either<out L, out R> {
    /** * Represents the exception side of [Either] class. */
    data class Exception<out L>(val exception: L) : Either<L, Nothing>() {
        val value: L get() = exception
    }

    /** * Represents the success side of [Either] class. */
    data class Success<out R>(val result: R) : Either<Nothing, R>() {
        val value: R get() = result
    }

    val isException get() = this is Exception<L>
    val isSuccess get() = this is Success<R>

    fun <L> exception(a: L) = Exception(a)
    fun <R> success(b: R) = Success(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Exception -> fnL(exception)
            is Success -> fnR(result)
        }
}
