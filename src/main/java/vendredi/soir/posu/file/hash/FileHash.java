package vendredi.soir.posu.file.hash;

import vendredi.soir.posu.PojaGenerated;

@PojaGenerated
public record FileHash(FileHashAlgorithm algorithm, String value) {}
