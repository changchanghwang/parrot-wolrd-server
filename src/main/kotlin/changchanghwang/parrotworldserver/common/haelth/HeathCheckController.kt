package changchanghwang.parrotworldserver.common.haelth

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HeathCheckController {
    @GetMapping("/ping")
    fun ping(): String {
        return "pong"
    }
}
