package com.base.common.domain

import com.base.applicationcommon.IDispatchers

/**
 * Abstract UseCase class.
 *
 * Extend this class to implement UseCase.
 */
abstract class UseCase(
    val dispatchers: IDispatchers
)
