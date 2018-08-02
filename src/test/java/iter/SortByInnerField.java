package iter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByInnerField {

  public static void main(String[] args) {
    List<Named> toSortList = new ArrayList<>();

    toSortList.add(new OuterA("a", "a1"));
    toSortList.add(new OuterA("aaa", "a2"));
    toSortList.add(new OuterA("aa", "a3"));
    toSortList.add(new OuterA("aaaaa", "a4"));
    toSortList.add(new OuterA("aaaa", "a5"));
    toSortList.add(new OuterB("Bbbbb", "b1"));
    toSortList.add(new OuterB("Bbbb", "b2"));
    toSortList.add(new OuterB("Bbb", "b3"));
    toSortList.add(new OuterB("Bb", "b4"));
    toSortList.add(new OuterB("B", "b5"));

    Comparator<Named> byNameLength = (name1, name2) -> name1.getName().length() > name2.getName().length() ? 1 : -1;
    Collections.sort(toSortList, byNameLength);
    for (Named named : toSortList) {
      System.out.println(named);
    }
  }
}

interface Named {
  String getName();
}

class OuterA implements Named {
  String name;
  String diff1;

  public OuterA(String name, String diff1) {
    this.name = name;
    this.diff1 = diff1;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDiff1() {
    return diff1;
  }

  public void setDiff1(String diff1) {
    this.diff1 = diff1;
  }

  public String toString() {
    return "diff1: " + diff1 + ", name: " + name;
  }
}

class OuterB implements Named {
  String name;
  String diff2;

  public OuterB(String name, String diff2) {
    this.name = name;
    this.diff2 = diff2;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDiff2() {
    return diff2;
  }

  public void setDiff2(String diff2) {
    this.diff2 = diff2;
  }

  public String toString() {
    return "diff2: " + diff2 + ", name: " + name;
  }
}
