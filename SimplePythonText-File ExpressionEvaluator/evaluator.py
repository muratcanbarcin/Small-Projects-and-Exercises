#İnput dosyasını satır satır okumaya yarayan fonksiyon
def file_read(line_num):
    input_file = open("input.txt", "r", encoding="utf-8")
    for j in range(line_num+1):
        file_line_read = input_file.readline()
    input_file.close()
    return file_line_read
#Output dosyasını oluşturan fonksiyon
def file_append(result):
    output_file = open("output.txt", "a", encoding="utf-8")
    output_file.write("{}\n".format(result))
    output_file.close()
    print("")
#İnput satırlarındaki boşlukları silen fonksiyon
def delete_space(file_line_read):
    if type(file_line_read) == str:
        delete_list = list(file_line_read)
        if delete_list[-1] =="\n":
            del delete_list[-1]
    else:
        delete_list = file_line_read
    counter =0
    for ch in delete_list:
        if ch == " " or ch is None or ch =="":
            del delete_list[counter]
        counter +=1
    return delete_list
#Birden çok basamağa sahip sayıları bulan fonksiyon
def find_number(delete_list):
    arith_control_list = ["+","-", "*", "/", "%"]
    logic_control_list = [">", "<", "!", "="]
    total_list = logic_control_list + arith_control_list
    delete_list_term =[]
    first = 0
    counter = 0
    number = ""
    for i in range(len(delete_list)):
        index = i
        number =""
        for ch in range(len(total_list)):
            if delete_list[i] == total_list[ch]:
                for j in range(first,index):
                    number +=delete_list[j]
                first = index+1
                delete_list_term.insert(counter,number)
                delete_list_term.insert(counter+1,total_list[ch])
                counter+=2
    number = ""
    for m in range(first,len(delete_list)):
        number += delete_list[m]

    delete_list_term.insert(counter, number)
    delete_list = delete_list_term

    return delete_list
#Yapılacak işlemin logic mi aritmetik mi olduğunu bulan fonksiyon
def find_logic_or_arith(delete_list,numbers):
    arith_control_list = ["+","-","*", "/", "%"]
    logic_control_list = [">", "<", "!", "="]

    line_op =" "
    for i in delete_list:
        control = 0
        for j in arith_control_list:
            if control ==1:
                break
            if j == i:
                if line_op == "logic":
                    line_op = "logic+arith"
                    control =1
                    break
                elif line_op == "logic+arith":
                    control =1
                else:
                    line_op = "arith"
                    control = 1
                    break

        for k in logic_control_list:
            if control ==1:
                break
            if i == k:
                if line_op == "arith":
                    line_op = "logic+arith"
                    control =1
                    break
                elif line_op == "logic+arith":
                    control =1
                else:
                    line_op ="logic"
                    control =1
                    break
        for m in numbers:
            if control ==1:
                break
            elif i == m:
                control =1
                break
        if control == 0:
            line_op = "ERROR"
            break

    if line_op == " ":
        line_op = "Number"

    return line_op
#Errorları bulan fonksiyon
def find_error(delete_list,line_op,numbers):
    arith_control_list = ["+","-" ,"*", "/", "%"]
    logic_control_list = [">", "<", "!","="]
    total_list = arith_control_list + logic_control_list

    Error =0
    loop_counter = 0

    for last_ch in total_list:
        if last_ch == delete_list[-1] or last_ch == delete_list[0]:
            Error =1
            break

    if line_op =="logic":
        for element in range(len(delete_list)):
            index = element
            if index == len(delete_list) - 1:
                break
            for element_2 in range(len(logic_control_list)):
                if delete_list[element] == logic_control_list[element_2]:
                    if delete_list[element] =="=":
                        if delete_list[index+1] == "=":
                            loop_counter+=1
                    elif delete_list[index+1] == "=":
                        loop_counter+=1
                    else:
                        for n in numbers:
                            if delete_list[index+1] == n:
                                loop_counter +=1
                                Error =0
                                break
                            else:
                                Error =1
        if loop_counter >1:
            Error =1

    elif line_op =="arith":
        for element_3 in range(len(delete_list)):
            index = element_3
            if index == len(delete_list) - 1:
                break
            for element_4 in range(len(arith_control_list)):
                if delete_list[element_3] == arith_control_list[element_4]:
                    if delete_list[element_3] == "*" and delete_list[index+1] == "*":
                        continue
                    elif delete_list[element_3] =="/" and delete_list[index+1] =="/":
                        continue
                    else:
                        for n in numbers:
                            if delete_list[index+1] == n:
                                Error =0
                                break
                            else:
                                Error =1
    elif line_op =="logic+arith":
        Error =0

    else:
        Error =-1

    return Error

#İşlem önceliğine göre aritmetik ifadeleri yapan fonksiyon
def operator_precedence(delete_list):

    delete_list_term = delete_list.copy()
    while True:
        control =[]
        for p in range(len(delete_list) - 1):
            index = p
            if delete_list[p] == "*" and delete_list[index + 1] == "*":
                control.append(p)
                result = float(delete_list[index - 1]) ** float(delete_list[index + 2])
                delete_list_term[index - 1] = str(result)
                delete_list[index + 2] = str(result)
                del delete_list_term[index]
                del delete_list_term[index]
                del delete_list_term[index]
                break

        delete_list = delete_list_term.copy()
        if control == []:
            break


    def carpma(delete_list):
        while True:
            control = []
            for p_2 in range(len(delete_list) - 1):
                index = p_2
                if delete_list[p_2] == "*" and delete_list[index + 1] != "*":
                    control.append(p_2)
                    result_2 = float(delete_list[index - 1]) * float(delete_list[index + 1])
                    delete_list_term[index - 1] = str(result_2)
                    delete_list[index + 1] = str(result_2)
                    del delete_list_term[index]
                    del delete_list_term[index]
                    break

            delete_list = delete_list_term.copy()
            if control == []:
                break
        return delete_list


    def tam_bolme(delete_list):
        while True:
            control = []

            for p_3 in range(len(delete_list) - 1):
                index = p_3
                if delete_list[p_3] == "/" and delete_list[index + 1] == "/":
                    control.append(p_3)
                    result_3 = float(delete_list[index - 1]) // float(delete_list[index + 2])
                    delete_list_term[index - 1] = str(result_3)
                    delete_list[index + 2] = str(result_3)
                    del delete_list_term[index]
                    del delete_list_term[index]
                    del delete_list_term[index]
                    break

            delete_list = delete_list_term.copy()
            if control == []:
                break
        return delete_list
    def bolme(delete_list):
        while True:
            control = []

            for p_4 in range(len(delete_list) - 1):
                index = p_4
                if delete_list[p_4] == "/" and delete_list[index + 1] != "/" and delete_list[index-1] != "/" :
                    control.append(p_4)
                    result_4 = float(delete_list[index - 1]) / float(delete_list[index + 1])
                    delete_list_term[index - 1] = str(result_4)
                    delete_list[index + 1] = str(result_4)
                    del delete_list_term[index]
                    del delete_list_term[index]
                    break

            delete_list = delete_list_term.copy()
            if control == []:
                break
        return delete_list

    def mod(delete_list):
        while True:
            control = []
            for p_7 in range(len(delete_list) - 1):
                index = p_7
                if delete_list[p_7] == "%":
                    control.append(p_7)
                    result_7 = float(delete_list[index - 1]) % float(delete_list[index + 1])
                    delete_list_term[index - 1] = str(result_7)
                    delete_list[index + 1] = str(result_7)
                    del delete_list_term[index]
                    del delete_list_term[index]
                    break

            delete_list = delete_list_term.copy()
            if control == []:
                break
        return delete_list

    op_pre_list = []
    counter = 0
    op_list = ["*", "/", "%"]
    for c_1 in range(len(delete_list)):
        for c_2 in range(len(op_list)):
            if delete_list[c_1] == op_list[c_2]:
                if op_list[c_2] == "*":
                    op_pre_list.insert(counter, "*")
                    counter += 1
                elif op_list[c_2] == "/":
                    if c_1 == len(delete_list):
                        break
                    elif delete_list[c_1 + 1] == "/":
                        op_pre_list.insert(counter, "//")
                        counter += 1
                    elif delete_list[c_1-1] == "/":
                        counter+=0
                    else:
                        op_pre_list.insert(counter, "/")
                        counter += 1
                elif op_list[c_2] == "%":
                    op_pre_list.insert(counter, "%")
                    counter += 1
                else:
                    counter += 0

    for eleman in op_pre_list:
        if eleman == "*":
            delete_list= carpma(delete_list)
        elif eleman =="/":
            delete_list = bolme(delete_list)
        elif eleman =="//":
            delete_list = tam_bolme(delete_list)
        elif eleman =="%":
            delete_list = mod(delete_list)


    def toplama(delete_list):
        while True:
            control = []

            for p_5 in range(len(delete_list) - 1):
                index = p_5
                if delete_list[p_5] == "+":
                    control.append(p_5)
                    result_5 = float(delete_list[index - 1]) + float(delete_list[index + 1])
                    delete_list_term[index - 1] = str(result_5)
                    delete_list[index + 1] = str(result_5)
                    del delete_list_term[index]
                    del delete_list_term[index]
                    break

            delete_list = delete_list_term.copy()
            if control == []:
                break
        return delete_list


    def cikarma(delete_list):
        while True:
            control = []

            for p_6 in range(len(delete_list) - 1):
                index = p_6
                if delete_list[p_6] == "-":
                    control.append(p_6)
                    result_6 = float(delete_list[index - 1]) - float(delete_list[index + 1])
                    delete_list_term[index - 1] = str(result_6)
                    delete_list[index + 1] = str(result_6)
                    del delete_list_term[index]
                    del delete_list_term[index]
                    break

            delete_list = delete_list_term.copy()
            if control == []:
                break
        return delete_list

    op_list_2 =["+","-"]
    op_pre_list_2 = []
    counter_2 =0

    for c_3 in range(len(delete_list)):
        for c_4 in range(len(op_list_2)):
            if delete_list[c_3] == op_list_2[c_4]:
                if op_list_2[c_4] == "+":
                    op_pre_list_2.insert(counter_2, "+")
                    counter_2 += 1
                elif op_list_2[c_4] == "-":
                    op_pre_list_2.insert(counter_2, "-")
                    counter_2 += 1
                else:
                    counter_2+=0

    for eleman in op_pre_list_2:
        if eleman == "+":
            delete_list = toplama(delete_list)
        elif eleman =="-":
            delete_list = cikarma(delete_list)


    result_end = float(delete_list[0])

    return result_end

#logic ifadelerin karşılaştırmasını yapan fonksiyon
def logic_compare(delete_list):
    return_control =0

    for log in range(len(delete_list)):
        index = log

        if delete_list[log] == "<" and delete_list[index+1] == "=":
            if(float(delete_list[index-1])<= float(delete_list[index + 2])):
                return_control =1
                return True
            else:
                return_control =1
                return False

        elif delete_list[log] =="<":
            if (float(delete_list[index - 1]) < float(delete_list[index + 1])):
                return_control =1
                return True
            else:
                return_control =1
                return False
        elif delete_list[log] ==">" and delete_list[index+1]=="=":
            if (float(delete_list[index - 1]) >= float(delete_list[index + 2])):
                return_control = 1
                return True
            else:
                return_control = 1
                return False
        elif delete_list[log] ==">":
            if (float(delete_list[index - 1]) > float(delete_list[index + 1])):
                return_control = 1
                return True
            else:
                return_control = 1
                return False
        elif delete_list[log] =="=" and delete_list[index+1] =="=":
            if (float(delete_list[index - 1]) == float(delete_list[index + 2])):
                return_control = 1
                return True
            else:
                return_control = 1
                return False
        elif delete_list[log] =="!" and delete_list[index+1] =="=":
            if (float(delete_list[index - 1]) != float(delete_list[index + 2])):
                return_control = 1
                return True
            else:
                return_control = 1
                return False
    if return_control ==0:
        return "Error"
#Aritmetik operatör barındıran logic ifadelerin işlemlerini yapan fonksiyon
def logic_arith_op(delete_list):
    logic_control_list = [">", "<", "!","="]
    split_point = 0
    split_point_2=0
    term_value_2 =""
    for i in range(len(delete_list)):
        for j in range(len(logic_control_list)):
            if delete_list[i] == logic_control_list[j]:
                term_value = delete_list[i]
                split_point_2 = i
                if delete_list[i+1] == "=":
                    split_point=i+2
                    term_value_2 = "="
                    break
                else:
                    split_point =i+1
                    break

    liste_1 = delete_list[0:split_point_2]
    liste_2 = delete_list[split_point:]
    result_1 = operator_precedence(liste_1)
    result_2 = operator_precedence(liste_2)
    compare_list = []
    if term_value_2 != "":
        compare_list.insert(0,result_1)
        compare_list.insert(1,term_value)
        compare_list.insert(2,term_value_2)
        compare_list.insert(3,result_2)
    else:
        compare_list.insert(0, result_1)
        compare_list.insert(1, term_value)
        compare_list.insert(2, result_2)

    result =logic_compare(compare_list)

    return result
#Ana fonksiyon
def main():
    open("output.txt", "w")
    input_file= open("input.txt", "r+", encoding="utf-8")
    for lines in range(len(input_file.readlines())):
        try:
            file_line = file_read(lines)
            del_list = delete_space(file_line)
            if del_list == []:
                result=" "
                file_append(result)
                continue
            true_list = find_number(del_list)
            delete_list = delete_space(true_list)
            numbers = []
            for i in range(1000000):
                numbers.append(str(i))
            op_choice = find_logic_or_arith(delete_list, numbers)
            if op_choice == "Number":
                file_append(delete_list[0])
                continue
            is_error = find_error(delete_list, op_choice, numbers)

            if is_error == 1:
                result_10 = "ERROR"
                file_append(result_10)
            else:
                if op_choice == "arith":
                    result_11 = str(operator_precedence(delete_list))
                    file_append(result_11)
                elif op_choice == "logic":
                    result_12 = str(logic_compare(delete_list))
                    file_append(result_12)
                elif op_choice =="logic+arith":
                    result_13 = str(logic_arith_op(delete_list))
                    file_append(result_13)
                else:
                    result_14 = "ERROR"
                    file_append("ERROR")
        except:
            result ="ERROR"
            file_append(result)



main()

