package xrayfa.tun2socks

/**
 * control tun2socks function start/stop
 */
interface Tun2SocksService {


    suspend fun startTun2Socks(fd: Int)

    suspend fun stopTun2Socks()

}