package com.example.appnewsjava.data.pojo;

public class Rss {

    private Channel channel;

    public Rss(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
