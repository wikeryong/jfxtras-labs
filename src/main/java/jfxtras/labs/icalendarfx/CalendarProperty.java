package jfxtras.labs.icalendarfx;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jfxtras.labs.icalendarfx.components.VComponentBase;
import jfxtras.labs.icalendarfx.parameters.PropertyParameter;
import jfxtras.labs.icalendarfx.properties.calendar.CalendarScale;
import jfxtras.labs.icalendarfx.properties.calendar.Method;
import jfxtras.labs.icalendarfx.properties.calendar.ProductIdentifier;
import jfxtras.labs.icalendarfx.properties.calendar.Version;

public enum CalendarProperty
{
    CALENDAR_SCALE ("CALSCALE",
            Arrays.asList(PropertyParameter.VALUE_DATA_TYPES, PropertyParameter.OTHER),
             CalendarScale.class)
    {
        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
//            final String line;
//            if (contentLines.size() == 1)
//            {
//                line = contentLines.get(0);
//            } else
//            {
//                throw new IllegalArgumentException(toString() + " can only have one line of content");
//            }
            CalendarScale property = CalendarScale.parse(contentLine);
            vCalendar.setCalendarScale(property);
            return property;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            throw new RuntimeException("not implemented");
        }
    },
    METHOD ("METHOD",
            Arrays.asList(PropertyParameter.VALUE_DATA_TYPES, PropertyParameter.OTHER),
            Method.class)
    {
        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
//            final String line;
//            if (contentLines.size() == 1)
//            {
//                line = contentLines.get(0);
//            } else
//            {
//                throw new IllegalArgumentException(toString() + " can only have one line of content");
//            }
            Method property = Method.parse(contentLine);
            vCalendar.setMethod(property);
            return property;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            throw new RuntimeException("not implemented");
        }
    },
    PRODUCT_IDENTIFIER ("PRODID",
            Arrays.asList(PropertyParameter.VALUE_DATA_TYPES, PropertyParameter.OTHER),
            ProductIdentifier.class)
    {
        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
//            final String line;
//            if (contentLines.size() == 1)
//            {
//                line = contentLines.get(0);
//            } else
//            {
//                throw new IllegalArgumentException(toString() + " can only have one line of content");
//            }
            ProductIdentifier property = ProductIdentifier.parse(contentLine);
            vCalendar.setProductIdentifier(property);
            return property;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            throw new RuntimeException("not implemented");
        }
    },
    VERSION ("VERSION",
            Arrays.asList(PropertyParameter.VALUE_DATA_TYPES, PropertyParameter.OTHER),
            Version.class)
    {
        @Override
        public VChild parse(VCalendar vCalendar, String contentLine)
        {
//            final String line;
//            if (contentLines.size() == 1)
//            {
//                return PropertyType.VERSION.parse(vCalendar, contentLines.get(0));
//            } else
//            {
//                throw new IllegalArgumentException(toString() + " can only have one line of content");
//            }
            Version property = Version.parse(contentLine);
            vCalendar.setVersion(property);
            return property;
        }

        @Override
        public void copyChild(VChild child, VCalendar destination)
        {
            throw new RuntimeException("not implemented");
        }
    };
    
    // Map to match up name to enum
    private static Map<String, CalendarProperty> enumFromNameMap = makeEnumFromNameMap();
    private static Map<String, CalendarProperty> makeEnumFromNameMap()
    {
        Map<String, CalendarProperty> map = new HashMap<>();
        CalendarProperty[] values = CalendarProperty.values();
        for (int i=0; i<values.length; i++)
        {
            map.put(values[i].toString(), values[i]);
        }
        return map;
    }
    public static CalendarProperty enumFromName(String propertyName)
    {
        return enumFromNameMap.get(propertyName.toUpperCase());
    }
    
    // Map to match up class to enum
    private static Map<Class<? extends VElement>, CalendarProperty> enumFromClassMap = makeEnumFromClassMap();
    private static Map<Class<? extends VElement>, CalendarProperty> makeEnumFromClassMap()
    {
        Map<Class<? extends VElement>, CalendarProperty> map = new HashMap<>();
        CalendarProperty[] values = CalendarProperty.values();
        for (int i=0; i<values.length; i++)
        {
            map.put(values[i].myClass, values[i]);
        }
        return map;
    }
    /** get enum from map */
    public static CalendarProperty enumFromClass(Class<? extends VElement> myClass)
    {
        return enumFromClassMap.get(myClass);
    }
    
    private Class<? extends VElement> myClass;
    public Class<? extends VElement> getElementClass() { return myClass; }
    
    private String name;
    @Override
    public String toString() { return name; }
    
    private List<PropertyParameter> allowedParameters;
    public List<PropertyParameter> allowedParameters() { return allowedParameters; }

    CalendarProperty(String name, List<PropertyParameter> allowedParameters, Class<? extends VElement> myClass)
    {
        this.name = name;
        this.allowedParameters = allowedParameters;
        this.myClass = myClass;
    }

    /** Parses string and sets property.  Called by {@link VComponentBase#parseContent()} */
    abstract public VChild parse(VCalendar vCalendar, String contentLine);
    
    abstract public void copyChild(VChild child, VCalendar destination);
}
