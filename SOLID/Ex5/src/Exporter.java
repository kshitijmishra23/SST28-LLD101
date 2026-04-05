/**
 * Base contract for all exporters.
 *
 * Preconditions (what callers must provide):
 *   - req must not be null
 *   - req.title must not be null
 *   - req.body can be null (treated as empty)
 *
 * Postconditions (what exporters guarantee):
 *   - Always returns a non-null ExportResult
 *   - Never throws for valid inputs (as defined above)
 *   - result.bytes is never null
 *
 * Subtypes must NOT tighten these preconditions (e.g., rejecting long content).
 * If a format has internal limitations, it must handle them gracefully
 * (e.g., truncation with a note) rather than throwing an exception.
 */
public abstract class Exporter {

    /**
     * Export the given request. Must honor the base contract above.
     */
    public abstract ExportResult export(ExportRequest req);
}
