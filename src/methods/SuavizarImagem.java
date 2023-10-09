package methods;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SuavizarImagem extends ExtrairCores {
    public static void suavizarImagem(BufferedImage imagem) throws IOException {

        int largura = imagem.getWidth();
        int altura = imagem.getHeight();


        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        double[] filtro_gau = {
                0.0625, 0.125, 0.0625,
                0.125, 0.25, 0.125,
                0.0625, 0.125, 0.0625
        };

        for (int linha = 1; linha < largura - 1; linha++) {
            for (int coluna = 1; coluna < altura - 1; coluna++) {

                int px1 = imagem.getRGB(linha - 1, coluna - 1);
                int px2 = imagem.getRGB(linha - 1, coluna);
                int px3 = imagem.getRGB(linha - 1, coluna + 1);
                int px4 = imagem.getRGB(linha, coluna - 1);
                int px5 = imagem.getRGB(linha, coluna);
                int px6 = imagem.getRGB(linha, coluna + 1);
                int px7 = imagem.getRGB(linha + 1, coluna - 1);
                int px8 = imagem.getRGB(linha + 1, coluna);
                int px9 = imagem.getRGB(linha + 1, coluna + 1);

                Color cor1 = new Color(px1);
                Color cor2 = new Color(px2);
                Color cor3 = new Color(px3);
                Color cor4 = new Color(px4);
                Color cor5 = new Color(px5);
                Color cor6 = new Color(px6);
                Color cor7 = new Color(px7);
                Color cor8 = new Color(px8);
                Color cor9 = new Color(px9);

                double pixel = (
                        filtro_gau[0] * cor1.getRed() +
                        filtro_gau[1] * cor2.getRed() +
                        filtro_gau[2] * cor3.getRed() +
                        filtro_gau[3] * cor4.getRed() +
                        filtro_gau[4] * cor5.getRed() +
                        filtro_gau[5] * cor6.getRed() +
                        filtro_gau[6] * cor7.getRed() +
                        filtro_gau[7] * cor8.getRed() +
                        filtro_gau[8] * cor9.getRed()
                );

                if (pixel > 255) {
                    pixel = 255;
                } else if (pixel < 0){
                    pixel = 0;
                }

                Color novaCor = new Color((int) pixel, (int) pixel, (int) pixel);

                novaImagem.setRGB(linha, coluna, novaCor.getRGB());

            }
        }



        String caminho = CAMINHO_PADRAO + "img.jpg";

        ImageIO.write(novaImagem, "jpg", new File(caminho));

        JFrame janela = new JFrame("img.jpg");
        janela.setSize(1366, 768);
        ImageIcon icon = new ImageIcon(novaImagem);
        JLabel label = new JLabel(icon);
        janela.add(label);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
}

