package changchanghwang.parrotworldserver.common.auth.decorators

import changchanghwang.parrotworldserver.common.auth.application.AuthService
import changchanghwang.parrotworldserver.common.exceptions.Unauthorized
import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class RequestAuthArgumentResolverConfig(
    private val requestAuthResolver: RequestAuthResolver,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(requestAuthResolver)
    }
}

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthGuard()

@Component
class RequestAuthResolver(
    private val authService: AuthService,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AuthGuard::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Long {
        val servletRequest = webRequest.nativeRequest as HttpServletRequest
        val accessToken =
            servletRequest.cookies?.find {
                it.name == "accessToken"
            }?.value ?: throw Unauthorized("There is no token", "로그인에 실패하였습니다.")

        return authService.validateAccessToken(accessToken)
    }
}
