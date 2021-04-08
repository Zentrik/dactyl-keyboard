<!--This document was generated and is intended for rendering to HTML on GitHub. Edit the source files, not this file.-->

# Key cluster configuration options

Each heading in this document represents a recognized configuration key in [YAML files for the DMOTE application](configuration.md).

This specific document describes options for the general outline and position of any individual cluster of keys. One set of such options will exist for each entry in `key-clusters`, a section documented [here](options-main.md).

## Table of contents
- Parameter <a href="#user-content-matrix-columns">`matrix-columns`</a>
- Parameter <a href="#user-content-style">`style`</a>
- Parameter <a href="#user-content-aliases">`aliases`</a>
- Section <a href="#user-content-anchoring">`anchoring`</a>

## Parameter <a id="matrix-columns">`matrix-columns`</a>

A list of key columns. Columns are aligned with the user’s fingers. Each column will be known by its index in this list, starting at zero for the first item. Each item may contain:

- `rows-above-home`: An integer specifying the amount of keys on the far side of the home row in the column. If this parameter is omitted, the effective value will be zero.
- `rows-below-home`: An integer specifying the amount of keys on the near side of the home row in the column. If this parameter is omitted, the effective value will be zero.

For example, on a normal QWERTY keyboard, H is on the home row for purposes of touch typing, and you would probably want to use it as such here too, even though the matrix in this program has no necessary relationship with touch typing, nor with the matrix in your MCU firmware (TMK/QMK etc.). Your H key will then get the coordinates `[0, 0]` as the home-row key in the far left column on the right-hand side of the keyboard.

In that first column, to continue the QWERTY pattern, you will want `rows-above-home` set to 1, to make a Y key, or 2 to make a 6-and-^ key, or 3 to make a function key above the 6-and-^. Your Y key will have the coordinates `[0, 1]`. Your 6-and-^ key will have the coordinates `[0, 2]`, etc.

Still in that first column, to finish the QWERTY pattern, you will want `rows-below-home` set to 2, where the two keys below H are N (coordinates `[0, -1]`) and Space (coordinates `[0, -2]`).

The next item in the list will be column 1, with J as `[1, 0]` and so on. On the left-hand side of a keyboard with `main-body` → `reflect`, main-body key clusters are mirrored so that `[0, 0]` will be G instead of H in QWERTY, `[1, 0]` will be F instead of J, and so on.

## Parameter <a id="style">`style`</a>

Cluster layout style. One of:

- `standard`: Both columns and rows have the same type of curvature applied in a logically consistent manner.
- `orthographic`: Rows are curved somewhat differently. This creates more space between columns and may prevent key mounts from fusing together if you have a broad matrix.

## Parameter <a id="aliases">`aliases`</a>

A map of short names to specific keys by coordinate pair. These names can be used as anchors for other features.

## Section <a id="anchoring">`anchoring`</a>

Where to place the cluster. The concept of anchoring is explained [here](options-anchoring.md), along with the parameters available in this section.

⸻

This document was generated from the application CLI.
