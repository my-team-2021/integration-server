import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.junit.Test;

public class QrCodeTestService {
    @Test
    public void qrCodeTest() {
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("d:/qrcode.jpg"));

        // 识别二维码
        // decode -> "http://hutool.cn/"
        String decode = QrCodeUtil.decode(FileUtil.file("d:/qrcode.jpg"));
        System.out.println("decode:" + decode);

        //base64
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        String generateAsBase64 = QrCodeUtil.generateAsBase64("11111111111", config, "png");
        System.out.println("generateAsBase64:" + generateAsBase64);
    }
}