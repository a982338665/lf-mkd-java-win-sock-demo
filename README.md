# WinSockDemo

**1.网络基础：**
    
    1.网络是当前信息技术的第一推动力
    2.每个计算设备上都有若干个网卡
    3.每个网卡上都有单独的硬件地址，mac地址，全球唯一
        mac地址：全称Media Access Control Address
        介质访问地址，又名硬件地址
        每个网卡都有全球唯一的mac地址，跟mac电脑不是一个意思
    4.ip地址：每个网卡、机器都有一个或多个ip地址
        -IPV4：192.168.0.100 每段0-255
        -IPV6:128bit长，分成8段，每段4个16进制数
        -查询:
            win     ipconfig
            linux   ifconfig
    5.port端口：0-65535
        -0-1023已经被占用：80是web，23是telnet
        -1024-65535一般程序可使用
    6.机器通讯：ip+port
    7.查询：netstat -an
    8.保留ip：127.0.0.1 指本机
    9.公网（万维网，互联网）和内网（区域网）：
        -网络是分层的
        -最外层是公网
        -底下每层都是内网
        -ip地址可以在每个层次的网重用
        -tracert看当前机器和目标机器的访问中继：tracert ip （通过多少个网关或交换机才能到目标机器）
            C:\Users\Administrator>tracert www.baidu.com
            通过最多 30 个跃点跟踪
            到 www.a.shifen.com [61.135.169.125] 的路由:
              1     1 ms     1 ms     2 ms  XiaoQiang [192.168.31.1]
              2     2 ms     2 ms     2 ms  192.168.1.1
              3     4 ms     3 ms     3 ms  10.70.0.1
    10.通信协议：TCP/UDP qq的udp为主，tcp为辅
        TCP: Transmission COntrol Protocol
            -传输控制协议，面向连接的协议
            -两台机器的可靠无差错的数据传输
            -双向字节流传递
        UDP：User Datagram Protocol
            -用户数据报协议，面向无连接协议
            -不保证可靠地数据传输
            -速度快，也可以在较差网络下使用

**2.UDP编程：**
    
    1.计算机通讯：数据从一个ip:port出发，运输到另一个ip:port【发送方->接收方】
    2.udp:无连接无状态的通讯协议：
        -发送方发送消息，如果接收方刚好在，则可以接受，否则消息丢失
        -发送方也无法知道是否发送成功
        -UDP好处：简单，节省，经济
    3.udp实现：
        -DatagramSocket：通讯的数据管道
            -send和receive方法
            -可选，多网卡绑定一个ip和port
        -DatagramPacket
            -集装箱：封装数据
            -地址标签：目的地ip+port
        -实例：
            -无主次之分
            -接收方必须早于发起方执行
    
**3.tcp编程：**
    
    0.tcp协议：有链接，保证可靠地无误差的通信
    1.服务器：创建一个ServerSocket等待连接
    2.客户机：创建一个socket，连接到服务器
    3.服务器：ServerSocket接收到连接，创建一个Socket和客户机Socket建立专线连接
        后续服务器和客户机的对话，这一对socket会在一个单独的线程（服务器端）上运行
    4.服务器的ServerSocket继续等待连接，返回步骤1

    5.ServerSocket：服务器码头
        -需要绑定port
        -如有多块网卡，需要绑定一个ip地址
    6.Socket：运输通道
        -客户端需要绑定服务端的地址和port
        -客户端往socket输入流写入数据，送到服务端
        -客户端从socket输出流取服务端过来的数据
        -服务端反之亦然
    7.服务端：
        -服务端等待响应时，处于阻塞状态
        -服务端可以同时响应多个客户端
        -服务端每接收一个客户端，就启动一个独立的线程与之对应
        -客户端和服务端都可以选择关闭这对Socket通道
        -实例：
            -服务端先启动，且一直保留
            -客户端后启动，可以先退出

**4.http编程：**
    
    1.网页访问：
        -网页时特殊的网络服务（http，Hypertext Transfer Protocol）
            -在浏览器输入url地址
            -浏览器将连接到远程服务器上（ip+80port）
            -请求下载html，放在本地临时文件
            -浏览器展示
    2.http简介：--》 html获取到的永远都是html流
        -超文本传输协议Hypertext Transfer Protocol
        -用于从www服务器传输超文本到本地浏览器的传输协议
        -资源文件采用html编写，以url形式对外提供
    3.html：超文本标记语言
    4.访问方式：
        get：获取资源
        post：提交数据
        put：上传文件
        delete：删除文件
        head：保文头部
        options：询问支持的方法
        trace：追踪路径
        connect：用隧道协议连接代理
    5.http编程：
        java.net包
        URL
        URLConnection
    6.HttpClient:两个包
        -jdk自带的：从java9开始有
            -jdk9新增，10更新，jdk11正式发布
            -java.net.http包
            -取代URLConnection
            -支持http/1.1，http/2
            -实现大部分http方法
            -主要类：
                HttpClient
                HttpRequest
                HttpResponse
        -Apache HttpComponents的HttpClient
            -hc.apache.org,Apache出品
            -从HttpClient进化而来
            -是一个集成的java Http工具包
                -实现所有的http方法
                -支持自动转向
                -只会https协议
                -支持代理服务器等
    7.应用：
        爬出
        自动刷票
        各类web监控
        web测试
        
**5.nio：**

    1.传统的tcp和udp都是BIO：blocking i/o（阻塞io）
    2.NIO：Non-Blocking I/O 非阻塞io 也称new IO
        -提供非阻塞通讯等方式
        -避免同步io通讯效率过低
        -一个线程可以同时管理多个连接
        -减少线程多的压力
    3.jdk1.4引入，1.7升级nio2.0（包括AIO）
        -java.nio包
        -主要类：
            Buffer缓存区：
                -Buffer缓冲区，一个可以读写的内存区域
                            -ByteBuffer,CharBUffer,DoubleBuffer,IntBuffer,LongBuffer,SHortBuffer(StringBuffer不是Buffer缓存区)
                -四个主要属性：
                            -capacity容量，position读写位置
                            -limit接线，mark标记，用于重复一个读写操作
            Channel通道：
                -全双工的，支持读写（Stream流是单向的）
                -支持异步读写
                -和Buffer配合，提高效率
                -ServerSocketChannel服务器TCP socket介入通道，接收客户端
                -SokcetChannel TCP Socket通道，可支持阻塞/非阻塞通讯
                -DatagramChannel UDP通道
                -FileChannel文件通道
            Selector多路选择器              
                -每隔一段时间不断轮训注册在其上的Channel
                -如果有一个Channel有接入读写操作，就会被轮询出来
                -根据SelectionKey可以获取相应的Channel，进行后续IO操作
                -避免过多的线程
                -SelectionKey四种类型：
                    ·OP_CONNECT
                    ·OP_ACCEPT
                    ·OP_READ
                    ·OP_WRITE

**6.AIO；**
    
    BIO 阻塞io        一个线程管理一个连接，当有几亿数据访问时，不可能开启几亿条线程，一个机器顶多承受几百个线程
    NIO 非阻塞IO      一个线程管理多个连接，减少了线程多的压力
        NIO并不是一个异步的通讯模式，他是一个同步的通讯模式
        假设：点单
            同步阻塞：下单，等饭，直到做好，交接               BIO
            同步非阻塞：下单，不等待，定时轮询，等好了，交接    NIO 
            异步非阻塞：下单，不等待，不轮询，好了，送家里      AIO
        并发编程的同步：指多个线程需要以一种同步的方式来访问某一个数据结构，这里的同步反义词是非同步的，即线程不安全
        网络通讯的同步：指客户端与服务端直接的通讯等待方式，这里反义词是异步，无需等待另一端操作完成
    AIO Asynchronous I/O，异步I/O --> 通过回调函数通知
        jdk1.7引入，主要在java.nio包中
        异步i/o，采用回调方法进行处理读写操作
        主要类：
            -AsynchronousServerSocketChannel服务器接受请求通道：bind绑定在某一个端口 accept接收客户端请求
            -AsynconouSocketChannel Socket通讯通道 read读数据 write写数据
            -CompletionHandler异步处理类：
                ·completed操作完成后异步调用方法 
                ·failed操作失败后异步调用方法
