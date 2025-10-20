# Simple Python Text-File Expression Evaluator

## Projeye Genel Bakış

Bu proje, `input.txt` adlı bir metin dosyasındaki matematiksel ve mantıksal ifadeleri satır satır okuyan, bu ifadeleri değerlendiren ve sonuçları `output.txt` dosyasına yazan basit bir Python betiğidir.

Projenin en önemli kısıtlaması, `eval()` gibi hazır değerlendirme fonksiyonları veya herhangi bir harici Python kütüphanesi kullanılmamasıdır. Tüm ayrıştırma (parsing), tokenizasyon ve işlem önceliği (operator precedence) mantığı sıfırdan kodlanmıştır.

## Desteklenen Özellikler

* **Temel Aritmetik İşlemler:** Toplama (`+`), Çıkarma (`-`), Çarpma (`*`), Bölme (`/`).
* **İleri Aritmetik İşlemler:** Üs alma (`**`), Tam bölme (`//`) ve Modülüs (`%`).
* **Mantıksal Karşılaştırmalar:** `<`, `>`, `<=`, `>=`, `==` ve `!=`.
* **İşlem Önceliği:** Python'un standart işlem önceliği kurallarına uyar (örn. `**` işlemi `*` işleminden, `*`/`/` işlemleri `+`/`-` işlemlerinden önce yapılır).
* **Karmaşık İfadeler:** Hem aritmetik hem de mantıksal operatörleri içeren `5 * 3 + 10 > 4 / 2` gibi karmaşık satırları çözebilir.
* **Esnek Sözdizimi:** `5+3` (boşluksuz) ve `5 + 3` (boşluklu) gibi farklı formatlardaki ifadeleri işleyebilir.
* **Hata Yönetimi:** `5 + z` (geçersiz karakter) veya `1+` (eksik eleman) gibi geçersiz sözdizimine sahip satırlar için çıktı dosyasına "ERROR" yazar.
* **Boş Satır İşleme:** `input.txt` dosyasındaki boş satırlar, okunabilirliği korumak için `output.txt` dosyasına da boş satır olarak yansıtılır.

## Kod Mimarisi (Nasıl Çalışır)

Betik, `input.txt` dosyasını satır satır okuyan bir ana döngü (`main` fonksiyonu) üzerine kuruludur. Her satır için:

1.  **Okuma ve Temizleme:** Satır okunur (`file_read`) ve `delete_space` ile tüm gereksiz boşluklar kaldırılır.
2.  **Ayrıştırma (Tokenization):** `find_number` fonksiyonu, temizlenmiş dizeyi sayılar (çok basamaklıları bir bütün olarak) ve operatörler içeren bir listeye dönüştürür.
3.  **Sınıflandırma:** `find_logic_or_arith` fonksiyonu, ifadenin "aritmetik", "mantıksal" veya "karma (logic+arith)" olduğunu belirler.
4.  **Doğrulama:** `find_error` fonksiyonu, operatörlerin satırın başında veya sonunda olması gibi temel sözdizimi hatalarını kontrol eder.
5.  **Değerlendirme (Evaluation):**
    * **Aritmetik:** `operator_precedence` fonksiyonu, işlem önceliğine göre ifadeyi çözümler.
    * **Mantıksal:** `logic_compare` fonksiyonu çağrılır.
    * **Karma:** `logic_arith_op` fonksiyonu, ifadeyi mantıksal operatörden böler, her iki tarafı da `operator_precedence` ile çözer ve son olarak `logic_compare` ile karşılaştırır.
6.  **Yazma:** Elde edilen sonuç (veya "ERROR") `file_append` fonksiyonu ile `output.txt` dosyasına yazılır.

## Nasıl Kullanılır

1.  Proje dosyalarını indirin ve Python betiğinin adını `evaluator.py` olarak ayarlayın.
2.  Hesaplanmasını istediğiniz ifadeleri `input.txt` dosyasına her satıra bir ifade gelecek şekilde yazın.
3.  Aşağıdaki komutu kullanarak Python betiğini çalıştırın:

    ```bash
    python evaluator.py
    ```

4.  Sonuçlar, betikle aynı dizinde `output.txt` adlı dosyada oluşturulacaktır.

## Örnek

### `input.txt` Dosyası

```text
5 + 3 * 2
5**2 / 5
10 > 8
5 * 5 + 2 >= 30 - 5
99 +
5 / 0
10 % 3
