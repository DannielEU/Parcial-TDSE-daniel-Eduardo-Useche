package exercice.tdse.spring.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service
public class service {
    public String linearSearch(Map<String, String> params){
        List<Integer> listNumbers = this.toList(params);
        Integer num = this.value(params);
        String output = this.search(listNumbers,num);
        return this.outputschema(params, output, "LinearSearch");
    }

    public String binarySearch(Map<String, String> params){
        List<Integer> listNumbers = this.toList(params);
        Integer num = this.value(params);
        String output = this.searchBinary(listNumbers,num);
        return this.outputschema(params, output, "BinarySearch");
    }

    private String outputschema(Map<String, String> params, String output, String operation) {
        StringBuilder out = new StringBuilder();
        out.append("{").append("\n").append("operation:").append(operation+",").append("\n")
                .append("inputlist:").append(params.get("list")+",").append("\n")
                .append("value:").append(params.get("value")+",").append("\n")
                .append("output:").append(output).append("\n")
                .append("}");
        return out.toString();
    }

    private List<Integer> toList(Map<String,String> lista){
        String numbers = lista.get("list");
        List<Integer> listNumbers = new ArrayList<>();
        for(String number: numbers.split(",")){
            listNumbers.add(Integer.parseInt(number));
        }
        return listNumbers;
    }

    private Integer value(Map<String,String> lista){
        String numbers = lista.get("value");
        Integer returnvalue = Integer.parseInt(numbers);
        return returnvalue;
    }
    private String search(List<Integer> lista, Integer num){
        Integer found = null;
        for (Integer i: lista){
            if(i.equals(num)){
                found = i;
            }
        }
        if(found == null){
            return "-l";
        }else {
            return found.toString();
        }
    }
    private String searchBinary(List<Integer> lista, Integer num){
        List<Integer> listSorted = this.sorter(lista);
        String v = this.binary(listSorted, num);
        return v;
    }
    private List<Integer> sorter(List<Integer> lista) {
        List<Integer> listSorted = new ArrayList<>(lista);
        Collections.sort(listSorted);
        System.out.println(listSorted);
        return listSorted;
    }
    private String binary(List<Integer> lista, Integer num) {
        if (lista == null || lista.isEmpty()) {
            return "-1";
        }
        int midd = lista.size() / 2;
        int midValue = lista.get(midd);
        if (midValue==num) {
            return num.toString();
        } else if (midValue < num) {
            List<Integer> listCut = lista.subList(midd + 1, lista.size());
            return this.binary(listCut, num);
        } else {
            List<Integer> listCut = lista.subList(0, midd);
            return this.binary(listCut, num);
        }
    }


}
