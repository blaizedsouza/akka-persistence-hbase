package akka.persistence.journal.hbase

import com.typesafe.config.Config

/**
 *
 * @param table table to be used to store akka messages
 * @param family column family name to store akka messages in
 * @param partitionCount Number of regions the used Table is partitioned to.
 *                       Currently must be FIXED, and not change during the lifetime of the app.
 *                       Should be a bigger number, for example 10 even if you currently have 2 regions, so you can split regions in the future.
 * @param scanBatchSize when performing scans, how many items to we want to obtain per one next(N) call
 * @param replayDispatcherId dispatcher for fetching and replaying messages
 */
case class HBaseJournalConfig(
  table: String,
  family: String,
  partitionCount: Int,
  scanBatchSize: Int,
  replayDispatcherId: String
)

object HBaseJournalConfig {
  def apply(config: Config): HBaseJournalConfig = {
    HBaseJournalConfig(
      table = config.getString("table"),
      family = config.getString("family"),
      partitionCount = config.getInt("partition.count"),
      scanBatchSize = config.getInt("scan-batch-size"),
      replayDispatcherId = config.getString("replay-dispatcher")
    )
  }
}
