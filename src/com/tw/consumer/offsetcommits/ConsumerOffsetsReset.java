package com.tw.consumer.offsetcommits;

import static com.tw.consumer.base.Constants.SAMPLE_TOPIC;

import com.tw.consumer.base.BaseConsumer;
import com.tw.consumer.base.CommitType;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerOffsetsReset extends BaseConsumer {

  private void consumeFromCommitReset() throws InterruptedException {
    //Create BaseConsumer Properties
    Properties props = getBasicConsumerProperties();
    props.put("group.id", "newGroup");
    props.put("auto.offset.reset", "earliest");//TODO [earliest, latest, none] with a NEW group id and observe

    //Create Kafka BaseConsumer
    KafkaConsumer<String, String> consumer = getKafkaConsumer(props);

    consumer.subscribe(Collections.singletonList(SAMPLE_TOPIC));

    pollProcessCommit(consumer, CommitType.DEFAULT);
  }

  public static void main(String[] args) throws InterruptedException {
    ConsumerOffsetsReset consumer = new ConsumerOffsetsReset();
    consumer.consumeFromCommitReset();
  }

}
