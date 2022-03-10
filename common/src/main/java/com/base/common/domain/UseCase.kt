package com.base.common.domain

import com.base.common.IDispatchers

/**
 * Abstract UseCase class.
 *
 * Extend this class to implement UseCase.
 */
abstract class UseCase(
    val dispatchers: IDispatchers
)