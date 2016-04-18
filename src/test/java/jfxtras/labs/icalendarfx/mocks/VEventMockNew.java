package jfxtras.labs.icalendarfx.mocks;

import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.Collection;
import java.util.stream.Stream;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import jfxtras.labs.icalendarfx.components.VEventNew;
import jfxtras.labs.icalendarfx.properties.component.descriptive.Attachment;
import jfxtras.labs.icalendarfx.properties.component.descriptive.Categories;
import jfxtras.labs.icalendarfx.properties.component.descriptive.Location;
import jfxtras.labs.icalendarfx.properties.component.descriptive.Resources;
import jfxtras.labs.icalendarfx.properties.component.descriptive.Summary;
import jfxtras.labs.icalendarfx.properties.component.recurrence.ExDate;
import jfxtras.labs.icalendarfx.properties.component.relationship.Contact;
import jfxtras.labs.icalendarfx.properties.component.relationship.RelatedTo;

public class VEventMockNew extends VEventNew<VEventMockNew, InstanceMock>
{

    /*
     * CONSTRUCTORS
     */
    public VEventMockNew() { }
    
    public VEventMockNew(String contentLines)
    {
        super(contentLines);
    }
    
    @Override
    public Temporal getDateTimeEnd()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<Temporal> dateTimeEndProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDateTimeEnd(Temporal dtEnd)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getTimeTransparency()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringProperty timeTransparencyProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTimeTransparency(String transparency)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ObjectProperty<TemporalAmount> durationProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TemporalAmount getDuration()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDuration(TemporalAmount duration)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Pair<Double, Double> getGeographicPosition()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<Pair<Double, Double>> geographicPositionProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setGeographicPosition(ObjectProperty<Pair<Double, Double>> geo)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Location getLocation()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<Location> locationProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLocation(Location location)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getPriority()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public IntegerProperty priorityProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPriority(int priority)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Resources getResources()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<Resources> resourcesProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setResources(Resources resources)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Categories getCategories()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<Categories> categoriesProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCategories(Categories categories)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getClassification()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringProperty classificationProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setClassification(String classification)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Contact getContact()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<Contact> contactProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setContact(Contact contact)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ZonedDateTime getDateTimeCreated()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<ZonedDateTime> dateTimeCreatedProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDateTimeCreated(ZonedDateTime dtCreated)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ExDate getExDate()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<ExDate> exDateProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setExDate(ExDate exDate)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ZonedDateTime getDateTimeLastModified()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<ZonedDateTime> dateTimeLastModifiedProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDateTimeLastModified(ZonedDateTime dtLastModified)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Temporal getDateTimeRecurrence()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<Temporal> dateTimeRecurrenceProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDateTimeRecurrence(Temporal recurrence)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public RelatedTo getRelatedTo()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<RelatedTo> relatedToProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setRelatedTo(RelatedTo relatedTo)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getSequence()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public IntegerProperty sequenceProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSequence(int value)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getStatus()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StringProperty statusProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setStatus(String classification)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Stream<Temporal> streamLimitedByRange()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Temporal getStartRange()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setStartRange(Temporal start)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Temporal getEndRange()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setEndRange(Temporal end)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Collection<InstanceMock> makeInstances(Temporal start, Temporal end)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<InstanceMock> makeInstances()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<InstanceMock> instances()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Stream<Temporal> streamRecurrences(Temporal startTemporal)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObservableList<Attachment<?>> getAttachments()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAttachments(ObservableList<Attachment<?>> properties)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Summary getSummary()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ObjectProperty<Summary> summaryProperty()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSummary(Summary summary)
    {
        // TODO Auto-generated method stub
        
    }

}
