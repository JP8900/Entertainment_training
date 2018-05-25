package comp3350.entertainment_tracker.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class EntertainmentItem implements Parcelable {
    public final static int TYPE_TVSHOW = 0;
    public final static int TYPE_MOVIE = 1;
    public final static int TYPE_BOOK = 2;

    private int type;
    private String name;
    private String image; //name of image of this particular item
    private String description;
    private float avgRating;
    private float userRating;

	private EntertainmentItem(Parcel source) {
		type = source.readInt();
		name = source.readString();
		image = source.readString();
		description = source.readString();
		avgRating = source.readFloat();
		userRating = source.readFloat();
	}

    public EntertainmentItem(String image) {
        this.type = 0;
        this.name = "";
        this.image = image;
        this.description = "";
        this.avgRating = 0f;
        this.userRating = 0;
    }

    public EntertainmentItem(int type, String name, String image, String description, float avgRating) {
        this.type = type;
        this.name = name;
        this.image = image;
        this.description = description;
        this.avgRating = avgRating;
        this.userRating = 0;
    }

    public EntertainmentItem(int type, String name, String image, String description, float avgRating, float userRating) {
        this.type = type;
        this.name = name;
        this.image = image;
        this.description = description;
        this.avgRating = avgRating;
        this.userRating = userRating;
    }

    public int getItemType() { return type; }
    public String getItemName() { return name; }
    public String getItemImage() { return image; }
    public String getItemDescription() { return description; }
    public float getAvgRating() { return avgRating; }
    public float getUserRating() { return userRating; }
    public void setUserRating(float userRating){ this.userRating = userRating; }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if(object != null && object instanceof EntertainmentItem) {
			EntertainmentItem otherObject = (EntertainmentItem)object;

            if (otherObject.getItemName() != null)
                result = otherObject.getItemName().equalsIgnoreCase(this.name) &&
						otherObject.getItemType() == this.type;
        }

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
		dest.writeString(name);
		dest.writeString(image);
		dest.writeString(description);
		dest.writeFloat(avgRating);
		dest.writeFloat(userRating);
    }

    public static final Parcelable.Creator<EntertainmentItem> CREATOR =
			new Parcelable.Creator<EntertainmentItem>() {
				public EntertainmentItem createFromParcel(Parcel source) {
					return new EntertainmentItem(source);
				}

				public EntertainmentItem[] newArray(int size) {
					return new EntertainmentItem[size];
				}
	};


}
