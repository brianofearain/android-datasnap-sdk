package com.datasnap.android.eventsandproperties;

public class Campaign extends Property{

	private String campaignId;
    private String name;
    private Tags tags;
    private String communicationIds;
    
 /** private SubCampaign subCampaign;
    ],
    "targeting_rules": {
        "Propid": "targetingid",
        "rules": [
            {
                "Propid": "ruleid1",
                "name": "retailandclothingruleforbackofstore",
                "place_ids": [
                    "placeid1"
                ],
                "audience": {
                    "Education": [
                        "NoCollege",
                        "College",
                        "GradSchool"
                    ],
                    "Age": [
                        "18-24",
                        "25-34",
                        "35-44",
                        "45-55",
                        "55+"
                    ],
                    "Ethnicity": [
                        "Caucasian",
                        "AfricanAmerican",
                        "Asian",
                        "Hispanic",
                        "Other"
                    ],
                    "Kids": [
                        "NoKids",
                        "HasKids"
                    ],
                    "Gender": [
                        "Male",
                        "Female"
                    ],
                    "Interests": [
                        "Gettingfromheretothere",
                        "Biking",
                        "Automotive",
                        "Automotive.Cars"
                    ],
                    "Income": [
                        "$0-30k",
                        "$30-60k",
                        "$60-100k",
                        "100k+"
                    ]
                },
                "targeting_attributes": {
                    "store_type": "retail",
                    "product_type": "clothing"
                }
            },
            {
                "Propid": "ruleid2",
                "name": "mycoolrule2",
                "audience": {
                    "Education": [
                        "NoCollege",
                        "College",
                        "GradSchool"
                    ],
                    "Age": [
                        "18-24",
                        "25-34",
                        "35-44",
                        "45-55",
                        "55+"
                    ],
                    "Ethnicity": [
                        "Caucasian",
                        "AfricanAmerican",
                        "Asian",
                        "Hispanic",
                        "Other"
                    ],
                    "Kids": [
                        "NoKids",
                        "HasKids"
                    ],
                    "Gender": [
                        "Male",
                        "Female"
                    ],
                    "Interests": [
                        "Gettingfromheretothere",
                        "Biking",
                        "Automotive",
                        "Automotive.Cars"
                    ],
                    "Income": [
                        "$0-30k",
                        "$30-60k",
                        "$60-100k",
                        "100k+"
                    ]
                }
            }
        ]
    },
    "time_filter": {
        "monday": {
            "start": "08: 00",
            "end": "13: 00"
        },
        "tuesday": {
            "start": "08: 00",
            "end": "22: 00"
        }
    },
    "time_trigger": {
        "monday": "08: 00",
        "tuesday": "13: 00"
    },
    "communication": {
        "Propid": "commid"
    }
} */
	
    
    public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tags getPropTags() {
		return tags;
	}

	public void setPropTags(Tags tags) {
		this.tags = tags;
	}

	public String getCommunicationIds() {
		return communicationIds;
	}

	public void setCommunicationIds(String communicationIds) {
		this.communicationIds = communicationIds;
	}


	

}
