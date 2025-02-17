package com.example.myweatherapp.bean;

import java.util.List;

public class SevendayBean {

    /**
     * code : 200
     * updateTime : 2025-02-17T08:42+08:00
     * fxLink : https://www.qweather.com/en/weather/beijing-101010100.html
     * daily : [{"fxDate":"2025-02-17","sunrise":"07:06","sunset":"17:53","moonrise":"22:38","moonset":"09:06","moonPhase":"亏凸月","moonPhaseIcon":"805","tempMax":"6","tempMin":"-5","iconDay":"100","textDay":"晴","iconNight":"150","textNight":"晴","wind360Day":"45","windDirDay":"东北风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"180","windDirNight":"南风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"23","precip":"0.0","pressure":"1025","vis":"25","cloud":"20","uvIndex":"3"},{"fxDate":"2025-02-18","sunrise":"07:05","sunset":"17:54","moonrise":"23:39","moonset":"09:26","moonPhase":"亏凸月","moonPhaseIcon":"805","tempMax":"6","tempMin":"-4","iconDay":"100","textDay":"晴","iconNight":"151","textNight":"多云","wind360Day":"45","windDirDay":"东北风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"45","windDirNight":"东北风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"25","precip":"0.0","pressure":"1027","vis":"25","cloud":"25","uvIndex":"4"},{"fxDate":"2025-02-19","sunrise":"07:03","sunset":"17:55","moonrise":"","moonset":"09:50","moonPhase":"亏凸月","moonPhaseIcon":"805","tempMax":"5","tempMin":"-4","iconDay":"101","textDay":"多云","iconNight":"150","textNight":"晴","wind360Day":"225","windDirDay":"西南风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"45","windDirNight":"东北风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"28","precip":"0.0","pressure":"1029","vis":"25","cloud":"24","uvIndex":"4"},{"fxDate":"2025-02-20","sunrise":"07:02","sunset":"17:56","moonrise":"00:41","moonset":"10:18","moonPhase":"下弦月","moonPhaseIcon":"806","tempMax":"4","tempMin":"-5","iconDay":"100","textDay":"晴","iconNight":"151","textNight":"多云","wind360Day":"315","windDirDay":"西北风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"45","windDirNight":"东北风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"27","precip":"0.0","pressure":"1029","vis":"25","cloud":"25","uvIndex":"4"},{"fxDate":"2025-02-21","sunrise":"07:01","sunset":"17:57","moonrise":"01:45","moonset":"10:55","moonPhase":"残月","moonPhaseIcon":"807","tempMax":"4","tempMin":"-5","iconDay":"101","textDay":"多云","iconNight":"151","textNight":"多云","wind360Day":"45","windDirDay":"东北风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"0","windDirNight":"北风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"22","precip":"0.0","pressure":"1031","vis":"25","cloud":"12","uvIndex":"4"},{"fxDate":"2025-02-22","sunrise":"06:59","sunset":"17:58","moonrise":"02:47","moonset":"11:38","moonPhase":"残月","moonPhaseIcon":"807","tempMax":"6","tempMin":"-4","iconDay":"100","textDay":"晴","iconNight":"150","textNight":"晴","wind360Day":"315","windDirDay":"西北风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"270","windDirNight":"西风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"25","precip":"0.0","pressure":"1033","vis":"25","cloud":"10","uvIndex":"4"},{"fxDate":"2025-02-23","sunrise":"06:58","sunset":"18:00","moonrise":"03:45","moonset":"12:32","moonPhase":"残月","moonPhaseIcon":"807","tempMax":"7","tempMin":"-2","iconDay":"101","textDay":"多云","iconNight":"151","textNight":"多云","wind360Day":"180","windDirDay":"南风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"180","windDirNight":"南风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"19","precip":"0.0","pressure":"1029","vis":"25","cloud":"25","uvIndex":"4"}]
     * refer : {"sources":["QWeather"],"license":["CC BY-SA 4.0"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private ReferBean refer;
    private List<DailyBean> daily;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<DailyBean> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyBean> daily) {
        this.daily = daily;
    }

    public static class ReferBean {
        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }

    public static class DailyBean {
        /**
         * fxDate : 2025-02-17
         * sunrise : 07:06
         * sunset : 17:53
         * moonrise : 22:38
         * moonset : 09:06
         * moonPhase : 亏凸月
         * moonPhaseIcon : 805
         * tempMax : 6
         * tempMin : -5
         * iconDay : 100
         * textDay : 晴
         * iconNight : 150
         * textNight : 晴
         * wind360Day : 45
         * windDirDay : 东北风
         * windScaleDay : 1-3
         * windSpeedDay : 3
         * wind360Night : 180
         * windDirNight : 南风
         * windScaleNight : 1-3
         * windSpeedNight : 3
         * humidity : 23
         * precip : 0.0
         * pressure : 1025
         * vis : 25
         * cloud : 20
         * uvIndex : 3
         */

        private String fxDate;
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
        private String moonPhase;
        private String moonPhaseIcon;
        private String tempMax;
        private String tempMin;
        private String iconDay;
        private String textDay;
        private String iconNight;
        private String textNight;
        private String wind360Day;
        private String windDirDay;
        private String windScaleDay;
        private String windSpeedDay;
        private String wind360Night;
        private String windDirNight;
        private String windScaleNight;
        private String windSpeedNight;
        private String humidity;
        private String precip;
        private String pressure;
        private String vis;
        private String cloud;
        private String uvIndex;

        public String getFxDate() {
            return fxDate;
        }

        public void setFxDate(String fxDate) {
            this.fxDate = fxDate;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getMoonrise() {
            return moonrise;
        }

        public void setMoonrise(String moonrise) {
            this.moonrise = moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public void setMoonset(String moonset) {
            this.moonset = moonset;
        }

        public String getMoonPhase() {
            return moonPhase;
        }

        public void setMoonPhase(String moonPhase) {
            this.moonPhase = moonPhase;
        }

        public String getMoonPhaseIcon() {
            return moonPhaseIcon;
        }

        public void setMoonPhaseIcon(String moonPhaseIcon) {
            this.moonPhaseIcon = moonPhaseIcon;
        }

        public String getTempMax() {
            return tempMax;
        }

        public void setTempMax(String tempMax) {
            this.tempMax = tempMax;
        }

        public String getTempMin() {
            return tempMin;
        }

        public void setTempMin(String tempMin) {
            this.tempMin = tempMin;
        }

        public String getIconDay() {
            return iconDay;
        }

        public void setIconDay(String iconDay) {
            this.iconDay = iconDay;
        }

        public String getTextDay() {
            return textDay;
        }

        public void setTextDay(String textDay) {
            this.textDay = textDay;
        }

        public String getIconNight() {
            return iconNight;
        }

        public void setIconNight(String iconNight) {
            this.iconNight = iconNight;
        }

        public String getTextNight() {
            return textNight;
        }

        public void setTextNight(String textNight) {
            this.textNight = textNight;
        }

        public String getWind360Day() {
            return wind360Day;
        }

        public void setWind360Day(String wind360Day) {
            this.wind360Day = wind360Day;
        }

        public String getWindDirDay() {
            return windDirDay;
        }

        public void setWindDirDay(String windDirDay) {
            this.windDirDay = windDirDay;
        }

        public String getWindScaleDay() {
            return windScaleDay;
        }

        public void setWindScaleDay(String windScaleDay) {
            this.windScaleDay = windScaleDay;
        }

        public String getWindSpeedDay() {
            return windSpeedDay;
        }

        public void setWindSpeedDay(String windSpeedDay) {
            this.windSpeedDay = windSpeedDay;
        }

        public String getWind360Night() {
            return wind360Night;
        }

        public void setWind360Night(String wind360Night) {
            this.wind360Night = wind360Night;
        }

        public String getWindDirNight() {
            return windDirNight;
        }

        public void setWindDirNight(String windDirNight) {
            this.windDirNight = windDirNight;
        }

        public String getWindScaleNight() {
            return windScaleNight;
        }

        public void setWindScaleNight(String windScaleNight) {
            this.windScaleNight = windScaleNight;
        }

        public String getWindSpeedNight() {
            return windSpeedNight;
        }

        public void setWindSpeedNight(String windSpeedNight) {
            this.windSpeedNight = windSpeedNight;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPrecip() {
            return precip;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(String uvIndex) {
            this.uvIndex = uvIndex;
        }
    }
}
