using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace SimpleQuestionAnsweringSystem
{
    internal class Program
    {
        static void Main(string[] args)
        {
            //Değişkenler tanımlandı, gerekli dosyalar açıldı

            string line = "", expression = "", op_choice = " ", string_term = " ", corpus_line = " ";
            double x_value = 0.0;
            int index = 0;
            string[] line_array;
            string[] term_array;
            int[] corpus_array = new int[100];
            string[] corpus_str = new string[100];
            double result = 0.0;
            string[] pattern_array = new string[10];
            string[] stop_words = new string[] { "A", "AFTER", "AGAIN", "ALL", "AM", "AND", "ANY", "ARE", "AS", "AT", "BE", "BEEN", "BEFORE", "BETWEEN", "BOTH", "BUT", "BY", "CAN", "COULD", "FOR", "FROM", "HAD", "HAS", "HE", "HER", "HERE", "HIM", "IN", "INTO", "I", " IS ", "IT", "ME", "MY", "OF", "ON", "OUR", "SHE", "SO", "SUCH", "THAN", "THAT", "THE", "THEN", "THEY", "THIS", "TO", "UNTIL", "WE", "WAS", "WERE", "WITH", "YOU" };
            char[] punc_words = new char[] { '.', ',', ';', '’', '"', '-', '?', '+', '*', '/' };

            StreamReader questions = File.OpenText("questions.txt");

            //questions.txt dosyanın okunması için do-while loop kullanıldı
            do
            {
                line = questions.ReadLine();
                bool correction = true;

                //41 karakter ve altı olan satırlar matematiksel ya da pattern satırı olamayacağı için direkt soru kısmına geçirildi
                if (line.Length > 41)
                {
                    //Matematiksel işlemler için substring ile satırdaki gerekli kısım tespit edildi
                    if (line.Substring(0, 32) == "What is the result of expression")
                    {
                        correction = false;
                        try
                        {
                            //Gerekli ifade tespit edildi
                            int term = line.Length - 41;
                            expression = line.Substring(32, term);

                            //Operatör tespit edildi
                            if (expression.Contains("+"))
                            {
                                op_choice = "+";
                            }


                            else if (expression.Contains("-"))
                            {
                                op_choice = "-";
                            }
                            else if (expression.Contains("*"))
                            {
                                op_choice = "*";
                            }
                            else if (expression.Contains("/"))
                            {
                                op_choice = "/";
                            }

                            //İşleme göre split metodu kullanıldı, x'in değeri bulundu

                            char term_char = Convert.ToChar(op_choice);
                            line_array = expression.Split(term_char);


                            index = line.IndexOf("for");
                            x_value = Convert.ToDouble(Convert.ToString(line[index + 6]));
                            int counter = 1;

                            //İşlemin yapılması için for loop kulanıldı

                            for (int k = 0; k < line_array.Length; k++)
                            {
                                string term_str = Convert.ToString(line_array[k]);
                                index = term_str.IndexOf("x");
                                string op_1 = term_str.Substring(0, index);
                                string op_2 = term_str.Substring(index + 1);
                                double result_ops = (Math.Pow(x_value, Convert.ToDouble(op_2))) * Convert.ToDouble(op_1);
                                line_array[k] = Convert.ToString(result_ops);
                                if (op_choice == "+") { result += result_ops; }
                                else if (op_choice == "-")
                                {
                                    if (counter == 1) { result = result_ops; }
                                    else { result -= result_ops; }
                                    counter++;
                                }
                                else if (op_choice == "*")
                                {
                                    if (counter == 1) { result = result_ops; }
                                    else { result = result * result_ops; }
                                    counter++;

                                }
                                else if (op_choice == "/")
                                {
                                    if (counter == 1) { result = result_ops; }
                                    else { result = result / result_ops; }
                                    counter++;
                                }

                            }

                            Console.WriteLine("The result is " + result);

                        }

                        catch
                        {
                            Console.WriteLine("ERROR, PROGRAMI ÇALIŞTIRIRKEN HATA OLUŞTU!");
                        }




                    }

                    //Pattern için gerekli else if kullanıldı (substring metodu ile)
                    else if (line.Substring(0, 39) == "What are the top10 words in the pattern")
                    {


                        try
                        {
                            //corpus satırı hazır hale getirildi
                            StreamReader corpus = File.OpenText("corpus.txt");
                            correction = false;

                            int counter = 0;
                            int counter_2 = 0;
                            bool flag = true;
                            string term_2 = " ";
                            int term = line.Length - 41;
                            expression = line.Substring(40, term);
                            for (int dn_1 = 0; dn_1 < expression.Length; dn_1++)
                            {
                                if (expression[dn_1] == 'i') { expression = expression.Replace("i", "I"); }
                                else if (expression[dn_1] == 'İ') { expression = expression.Replace("İ", "I"); }
                            }
                            expression = expression.ToUpper();
                            //corpus satırındaki kelimeler ile do-while loop ile kontrol sağlandı
                            do
                            {
                                counter = 0;
                                corpus_line = corpus.ReadLine();

                                for (int i = 0; i < corpus_line.Length; i++)
                                {
                                    for (int j = 0; j < punc_words.Length; j++)
                                    {
                                        if (corpus_line[counter] == punc_words[j])
                                        {
                                            string_term = corpus_line.Remove(counter, 1);
                                            corpus_line = string_term;
                                            break;
                                        }

                                    }
                                    counter++;
                                }

                                for (int dn = 0; dn < corpus_line.Length; dn++)
                                {
                                    if (corpus_line[dn] == 'i') { corpus_line = string_term.Replace("i", "I"); }
                                    else if (corpus_line[dn] == 'İ') { corpus_line = string_term.Replace("İ", "I"); }
                                }

                                string_term = corpus_line.ToUpper();
                                corpus_line = string_term;
                                line_array = corpus_line.Split(' ');


                                for (int k = 0; k < line_array.Length; k++)
                                {
                                    flag = true;
                                    term_2 = line_array[k];
                                    if (term_2.Length == expression.Length)
                                    {
                                        for (int cont = 0; cont < expression.Length; cont++)
                                        {
                                            if (expression[cont] != '-' && expression[cont] != term_2[cont])
                                            {
                                                flag = false;
                                                break;
                                            }
                                        }
                                    }
                                    else { flag = false; }
                                    if (counter_2 >= 10) { break; }
                                    if (flag)
                                    {
                                        bool flag2 = true;
                                        for (int mcb = 0; mcb < pattern_array.Length; mcb++)
                                        {
                                            if (pattern_array[mcb] == term_2) { flag2 = false; break; }
                                        }
                                        if (flag2)
                                        {
                                            pattern_array[counter_2] = term_2; counter_2++;
                                        }

                                    }
                                }


                            } while (!corpus.EndOfStream);
                            corpus.Close();

                            for (int pt = 0; pt < pattern_array.Length; pt++)
                            {
                                Console.Write(pattern_array[pt] + " ");
                            }
                            Console.WriteLine();

                        }
                        catch
                        {
                            Console.WriteLine("ERROR");
                        }


                    }

                }

                //Eğer üstteki değerler sağlanmazsa correction bool değeri ile soru kısmına geçildi
                if (correction)
                {


                    try
                    {
                        //corpus satırı gerekli hale getirildi
                        StreamReader corpus = File.OpenText("corpus.txt");
                        int counter = 0;

                        for (int i = 0; i < line.Length; i++)
                        {
                            for (int j = 0; j < punc_words.Length; j++)
                            {
                                if (line[counter] == punc_words[j])
                                {
                                    string_term = line.Remove(counter, 1);
                                    line = string_term;
                                    break;
                                }

                            }
                            counter++;
                        }

                        for (int dn = 0; dn < string_term.Length; dn++)
                        {
                            if (string_term[dn] == 'i') { line = string_term.Replace("i", "I"); string_term = line; }
                            else if (string_term[dn] == 'İ') { line = string_term.Replace("İ", "I"); string_term = line; }
                        }
                        line = string_term;
                        line = line.ToUpper();
                        line_array = line.Split(' ');
                        counter = 0;
                        //corpus satırları karşılaştırma için do-while loop kullanıldı
                        do
                        {
                            int max = 1;
                            corpus_line = corpus.ReadLine();
                            corpus_str[counter] = corpus_line;

                            string_term = corpus_line;
                            for (int dn = 0; dn < corpus_line.Length; dn++)
                            {
                                if (corpus_line[dn] == 'i') { corpus_line = string_term.Replace("i", "I"); }
                                else if (corpus_line[dn] == 'İ') { corpus_line = string_term.Replace("İ", "I"); }
                            }
                            corpus_line = corpus_line.ToUpper();

                            for (int k = 0; k < line_array.Length; k++)
                            {
                                bool flag = true;
                                for (int s = 0; s < stop_words.Length; s++)
                                {
                                    if (line_array[k] == stop_words[s])
                                    {
                                        flag = false;
                                        break;
                                    }

                                }
                                if (!flag) { continue; }

                                else if (corpus_line.Contains(line_array[k]))
                                {
                                    corpus_array[counter] = max;
                                    max += 1;
                                }

                            }
                            counter += 1;

                        } while (!corpus.EndOfStream);

                        corpus.Close();

                        //Max eşleşmeye sahip satırlar bulundu ve yazdırıldı

                        string winners = " ";
                        int max_value = 0;
                        for (int cr = 0; cr < corpus_array.Length; cr++)
                        {
                            if (corpus_array[cr] == 0) { continue; }
                            else
                            {

                                if (corpus_array[cr] > max_value)
                                {
                                    max_value = corpus_array[cr];
                                    winners = Convert.ToString(cr);

                                }
                                else if (corpus_array[cr] == max_value)
                                {
                                    winners += ",";
                                    winners += Convert.ToString(cr);
                                }
                            }

                        }
                        if (max_value < 2)
                        {
                            winners = "No answer";
                            Console.WriteLine(winners);
                        }

                        else
                        {
                            term_array = winners.Split(',');

                            for (int win = 0; win < term_array.Length; win++)
                            {
                                Console.WriteLine(corpus_str[Convert.ToInt32(term_array[win])]);
                            }

                        }


                    }

                    catch
                    {
                        Console.WriteLine("ERROR");
                    }




                }


            } while (!questions.EndOfStream);
            questions.Close();


            Console.ReadLine();

        }
    }
}
