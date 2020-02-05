import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import com.todo1.tools.encryption.cipher.RSACipher;
import com.todo1.tools.encryption.encoder.Base64Encoder;
import com.todo1.tools.encryption.util.KeyUtil;

/*******************************************************************************
 * © TODO1 SERVICES, INC. ('TODO1') All rights reserved, 2000, 2017.
 * 
 * This work is protected by the United States of America copyright laws.
 * All information contained herein is and remains the property of 
 * TODO1 [and its suppliers, if any] .
 * Dissemination of this information or reproduction of this material 
 * is not permitted unless prior written consent is obtained
 * from TODO1 SERVICES, INC.
 ******************************************************************************/

/**
 * @author mvisconti
 *
 */
public class MainEncrypt {

	public static void main(String[] args) throws Exception {

		// pruebaotp Todo123456
		String plain = "-563926308|W2/CWOAJFW+w7uJ4rRm0DX5/yRg/N+CIn88R8xg5Ae61EfGXIpSmvT1oiRDVzv0UU7sdhw==";
		// plain= salt_del_getParams|HP

		// plain= salt|clave para encriptar primera vez (con dummy falla primer servicio
		// si empieza del 1 al 4) plain = "199363995|Todo1234";
		plain = "1001642517|303578";
		//plain = "1513816470|804232";
		// plain agus, tarjeta no propia
		//plain="5470620291087687";
		// plain = "329282968|W2/CWOAJFW+w7uJ4rRm0DX5/yRg/N+CIn88R8xg5Ae61EfGXIpSmvT1oiRDVzv0UU7sdhw==";
		String llavePublica = "00f4849fcf14bb554fdc74e076e606e9807ce1cb3f840f53e3e2812a8a3358abc216999dbc08a19eeceee3690fdc6c4474f0da113ef69b08ab25aa98ace6462a461704e38613139e4d977356e25145ca0b67dc1d1cf5ab55c651129d7900d95d9d7d56c0672cecc8206a8f7851b0f1289c7b28c09159bef3c361f7416af078805a1b0b5f8d95a04e7777cc0c517e7714ce732124caa7e73fa2d22cb80f05417c94f9369839c446ae9feff715434001005f70d614b084619960c934eb1477abff7801d0f81294b9899489aa36ae1dcf63e1efffadbbe26aa931f2d503fefc1b834401b0504690925eb72c0ba9e3b111063bed78c727e16505918c895d9a945975fd";
		llavePublica = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA9ISfzxS7VU/cdOB25gbpgHzhyz+ED1Pj4oEqijNYq8IWmZ28CKGe7O7jaQ/cbER08NoRPvabCKslqpis5kYqRhcE44YTE55Nl3NW4lFFygtn3B0c9atVxlESnXkA2V2dfVbAZyzsyCBqj3hRsPEonHsowJFZvvPDYfdBavB4gFobC1+NlaBOd3fMDFF+dxTOcyEkyqfnP6LSLLgPBUF8lPk2mDnERq6f7/cVQ0ABAF9w1hSwhGGZYMk06xR3q/94AdD4EpS5iZSJqjauHc9j4e//rbviaqkx8tUD/vwbg0QBsFBGkJJetywLqeOxEQY77XjHJ+FlBZGMiV2alFl1/QIDAQAB";
		String llavePrivada = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQD0hJ/PFLtVT9x04HbmBumAfOHLP4QPU+PigSqKM1irwhaZnbwIoZ7s7uNpD9xsRHTw2hE+9psIqyWqmKzmRipGFwTjhhMTnk2Xc1biUUXKC2fcHRz1q1XGURKdeQDZXZ19VsBnLOzIIGqPeFGw8SiceyjAkVm+88Nh90Fq8HiAWhsLX42VoE53d8wMUX53FM5zISTKp+c/otIsuA8FQXyU+TaYOcRGrp/v9xVDQAEAX3DWFLCEYZlgyTTrFHer/3gB0PgSlLmJlImqNq4dz2Ph7/+tu+JqqTHy1QP+/BuDRAGwUEaQkl63LAup47ERBjvteMcn4WUFkYyJXZqUWXX9AgMBAAECggEBANVcTJ5DtttQXkvV+anqaiHl1pilibRePUFN+DKzbzIgn9vXWVYmwSh5oxHv9yS571iiz92jDdDYlntv0Z71prmWKHvoaAj8XzRCPpQo2r7ciPn2pWhw4t2zrIygI2+IUn4mDql49/wA3TrngFDnfcpdZUfzDN6kkd6QBBMuVdeYE8xNKI9JvLKQSGfjIcfxj2fboeTbzsLU9xPy/GDZ6RB15VtL6l69BojQOk7dAGmfZDYMyLkBTJszQn8sxbW+7HTlL7aTSLu5/FP8mBSRid1S2YUNoZuYNDVr0XgMX9Qc4NRu6PIxorMd/BCdmk4Vc+NTxqy+/fWpFDFV4X/lzEECgYEA/Qsd6uWcXFg4Mvql7fzTC1YA8N/31OADBbawMaCYQGAsTmM3p9l0ONWMwuWTZI0L0n2pU2r6FHP5/ZEAmXc/g0Qx/1yWCK+jYJNoYDXY+JLEtfW65hjNrZ4ymDXGnXZDVITy/kV7lyKNfuCi7m9xd0xk9mW4t2g4ZMwrdKXCQSkCgYEA92ABy4e+Do5jupbVcvjJenFjmE6m7FrvFsces/w2FoLuWSWYEgLZM19A3Pvqu2GSv6LTR8zoZNflMFbrnoTzOvY3TJqVpULlzHDqhk5qjLpTxU9KYZ7ERCfL6AfbAKZHaWoS9JNZFVO9uEc+lJzomznWzt3xOkRiXB0X/fTuxLUCgYBIonRIBPjJGRmoqIIQuk3qJxnGCsQc+uBjN3mEBVEuyWxuY9SJ/cV6uvHY0AVrC4GlPiToqQB39oJ/4quFYdF9YInpfy4h+CixhB6l308UqyyD/rFTczM27v4e6vDEKjpRfIgzrMY5L9Xl4Z1jS+IKGIeyoqhPHT9yz5h5t0r3YQKBgQDS1VCyVw70+QamkMd7Je8SHHH17ZL2/smncC4K2HqA1TavZAsnzqWv0w9FSK/W5GJeUjggidPHnvoHKQ3IHSfwqMiY0WeZYlrY06QOs+a76lJbaCjM6rP8Iq73IzrUYuOaRH3DVZInSyf3V0FPCCx3n+Qki6R9NKtgNqusFoUpwQKBgAZJAeXwI2R8S6qLProOiEZhGMwrlijZXg33QNBEAKRjMC5KWEpLcWnVN6iXIAPWYrgTlsuyLPpqu306kqssM5k5/UPv1/AlxCR5ocX8iCo2Hn0GqJ2sx4X4XiiPtGBTmtQX5fVA6CNQW6wSSD2xeX/9ScXOw+BvYhvA2210VkrL";

		String encr = encriptar(llavePublica, plain);

		System.out.println("Encriptado: " + encr);
		System.out.println("Desencriptado: " + desencriptar(llavePrivada, encr));

//		String encr2 = encriptarConModulus(plain);
//		System.out.println("Encriptado 2: " + encr2);

//		System.out.println("Desencriptado: " + desencriptar(llavePrivada, encr2));
//	 System.out.println("Desencript ado 2: " + desencriptarConModulus(encr2));
	}

	private static String encriptarConModulus(String plainText) throws Exception {
		String modulusGetParams = "00f4849fcf14bb554fdc74e076e606e9807ce1cb3f840f53e3e2812a8a3358abc216999dbc08a19eeceee3690fdc6c4474f0da113ef69b08ab25aa98ace6462a461704e38613139e4d977356e25145ca0b67dc1d1cf5ab55c651129d7900d95d9d7d56c0672cecc8206a8f7851b0f1289c7b28c09159bef3c361f7416af078805a1b0b5f8d95a04e7777cc0c517e7714ce732124caa7e73fa2d22cb80f05417c94f9369839c446ae9feff715434001005f70d614b084619960c934eb1477abff7801d0f81294b9899489aa36ae1dcf63e1efffadbbe26aa931f2d503fefc1b834401b0504690925eb72c0ba9e3b111063bed78c727e16505918c895d9a945975fd";

		String llavePublicaMua = "A6CA1BB4BD803E5704A071E8F7370FD68F2A42CAB574A765693F0F54796CB8AD2CF1B624005119FE651227F7992FF6A6D1979C9B72EA0EAD789F1CBADAB9851779CB8F5F82F40BC71C5C303A10298ED6DC5657E3401AE5720F06836F098366441AC30AB35F13FAB8B6CE81955A1181FCA0AD4EA471CC09C51EAE8EDA42E8C615F933483449CBC67883F407430CB856E4EEC1919BFDD38850CCF5837EC67D8CF802EC30836099592FCDB6CEF4D4AB8EC7F95229B6B262DC6F9A62BFD082CCF98D8FC73FADFA2CCBDDBD17126206E0EC41FE85ECDB9B7631A7EDEF193E4971ADA3E4AB3FFE05F5146907255AD29D0AFB91160C95E225514E1CD07E35BA157A44D1";

		RSACipher cipher = new RSACipher();
		cipher.setPadding("PKCS1Padding");
		BigInteger modulus = new BigInteger(modulusGetParams, 16);
		BigInteger pubExp = new BigInteger("010001", 16);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec ks = new RSAPublicKeySpec(modulus, pubExp);
		PublicKey pubKey = (PublicKey) keyFactory.generatePublic(ks);
		byte[] encrypted = cipher.encrypt(pubKey, plainText.getBytes("UTF-8"));
		return Base64Encoder.encodeBase64(encrypted);
	}

	private static String desencriptarConModulus(String plainText) throws Exception {
		String modulusGetParams = "00f4849fcf14bb554fdc74e076e606e9807ce1cb3f840f53e3e2812a8a3358abc216999dbc08a19eeceee3690fdc6c4474f0da113ef69b08ab25aa98ace6462a461704e38613139e4d977356e25145ca0b67dc1d1cf5ab55c651129d7900d95d9d7d56c0672cecc8206a8f7851b0f1289c7b28c09159bef3c361f7416af078805a1b0b5f8d95a04e7777cc0c517e7714ce732124caa7e73fa2d22cb80f05417c94f9369839c446ae9feff715434001005f70d614b084619960c934eb1477abff7801d0f81294b9899489aa36ae1dcf63e1efffadbbe26aa931f2d503fefc1b834401b0504690925eb72c0ba9e3b111063bed78c727e16505918c895d9a945975fd";

		String llavePublicaMua = "A6CA1BB4BD803E5704A071E8F7370FD68F2A42CAB574A765693F0F54796CB8AD2CF1B624005119FE651227F7992FF6A6D1979C9B72EA0EAD789F1CBADAB9851779CB8F5F82F40BC71C5C303A10298ED6DC5657E3401AE5720F06836F098366441AC30AB35F13FAB8B6CE81955A1181FCA0AD4EA471CC09C51EAE8EDA42E8C615F933483449CBC67883F407430CB856E4EEC1919BFDD38850CCF5837EC67D8CF802EC30836099592FCDB6CEF4D4AB8EC7F95229B6B262DC6F9A62BFD082CCF98D8FC73FADFA2CCBDDBD17126206E0EC41FE85ECDB9B7631A7EDEF193E4971ADA3E4AB3FFE05F5146907255AD29D0AFB91160C95E225514E1CD07E35BA157A44D1";

		RSACipher cipher = new RSACipher();
		cipher.setPadding("PKCS1Padding");
		BigInteger modulus = new BigInteger(modulusGetParams, 16);
		BigInteger pubExp = new BigInteger("010001", 16);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPrivateKeySpec ks = new RSAPrivateKeySpec(modulus, pubExp);
		PrivateKey privKey = (PrivateKey) keyFactory.generatePrivate(ks);
		byte[] encrypted = cipher.decrypt(privKey, plainText.getBytes("UTF-8"));
		return Base64Encoder.encodeBase64(encrypted);
	}

	private static String encriptar(String publicKeyWithX509EncodingBase64, String plainText) throws Exception {

		RSACipher cipher = new RSACipher();
		cipher.setPadding("PKCS1Padding");
		byte[] publicKeyWithX509Encoding = Base64Encoder.decodeBase64(publicKeyWithX509EncodingBase64);
		PublicKey publicKey = KeyUtil.getRSAPublicKeyFromX509EncodedKey(publicKeyWithX509Encoding);
		byte[] encrypted = cipher.encrypt(publicKey, plainText.getBytes("UTF-8"));
		return Base64Encoder.encodeBase64(encrypted);
	}

	private static String desencriptar(String privateKeyWithPcks8EncodingBase64, String codedText) throws Exception {

		byte[] privateKeyWithPcks8Encoding = Base64Encoder.decodeBase64(privateKeyWithPcks8EncodingBase64);
		PrivateKey privateKey = KeyUtil.getRSAPrivateKeyFromPKCS8EncodedKey(privateKeyWithPcks8Encoding);
		RSACipher cipher = new RSACipher();
		cipher.setPadding("PKCS1Padding");
		byte[] encryptedBytes = Base64Encoder.decodeBase64(codedText);

		return new String(cipher.decrypt(privateKey, encryptedBytes), "UTF-8");
	}

}
