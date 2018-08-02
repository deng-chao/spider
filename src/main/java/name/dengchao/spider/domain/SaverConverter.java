package name.dengchao.spider.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import name.dengchao.spider.saveto.SaveTo;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@Component
public class SaverConverter implements Converter {

  @Autowired
  ApplicationContext ctx;

  @Override
  public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
    return type.equals(Saver.class);
  }

  @Override
  public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {

    Saver saver = (Saver) source;
    writer.addAttribute("val", saver.getVal().toString());
    writer.addAttribute("path", saver.getPath());
  }

  @Override
  @SuppressWarnings("unchecked")
  public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
    String clzStr = reader.getAttribute("val");
    String path = reader.getAttribute("path");
    Saver saver = new Saver();
    saver.setPath(path);
    Class<? extends SaveTo> clz = null;
    try {
      SaveTo saveTo = null;
      clz = (Class<? extends SaveTo>) Class.forName(clzStr);
      saveTo = lookupFromCtx(clz);
      if (saveTo == null) {
        saveTo = clz.newInstance();
        saveTo.setPath(path);
      }
      saveTo.initial();
      saver.setVal(saveTo);
    } catch (Exception e) {
      throw new RuntimeException("Unmarshal Failed", e);
    }
    return saver;
  }

  SaveTo lookupFromCtx(Class<? extends SaveTo> clz) {
    try {
      return ctx.getBean(clz);
    } catch (Exception e) {
      return null;
    }
  }
}
