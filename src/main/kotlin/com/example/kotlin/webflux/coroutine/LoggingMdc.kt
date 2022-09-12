package com.example.kotlin.webflux.coroutine

import kotlinx.coroutines.slf4j.MDCContext
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.MDC
import org.springframework.core.KotlinDetector
import org.springframework.stereotype.Component
import java.util.*
import kotlin.coroutines.Continuation

@Aspect
@Component
class LoggingMdc {

    @Around(value = """@annotation(LogExecutionTime)""")
    fun addCoroutineMdc(joinPoint: ProceedingJoinPoint): Any? {
        val signature = joinPoint.signature as MethodSignature
        val method = signature.method

        return if (KotlinDetector.isSuspendingFunction(method)) {
            @Suppress("UNCHECKED_CAST")
            val continuation = joinPoint.args.last() as Continuation<Any?>

            MDC.put(MDC_KEY_TRACE_ID, UUID.randomUUID().toString())

            val newContext = continuation.context.plus(MDCContext())
            val newContinuation = Continuation<Any?>(newContext) { continuation.resumeWith(it) }

            val newArgs = joinPoint.args.dropLast(1).plus(newContinuation)

            joinPoint.proceed(newArgs.toTypedArray())
        } else {
            joinPoint.proceed()
        }
    }

    companion object {
        private const val MDC_KEY_TRACE_ID = "traceId"
    }

}
