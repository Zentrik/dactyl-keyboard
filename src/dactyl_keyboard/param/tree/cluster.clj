;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; The Dactyl-ManuForm Keyboard — Opposable Thumb Edition              ;;
;; Parameter Specification – Key Clusters                              ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns dactyl-keyboard.param.tree.cluster
  (:require [clojure.spec.alpha :as spec]
            [dactyl-keyboard.param.schema.anch :as anch]
            [dactyl-keyboard.param.schema.parse :as parse]
            [dactyl-keyboard.param.schema.valid :as valid]
            [dactyl-keyboard.param.stock :as stock]))

(def raws
  "A flat version of a special part of a user configuration.
  Default values and parsers here are secondary. Validators are used."
  [["# Key cluster configuration options\n\n"
    "Each heading in this document represents a recognized configuration key "
    "in [YAML files for the DMOTE application](configuration.md).\n\n"
    "This specific document describes options for the general outline "
    "and position of any individual cluster of keys. One set of such "
    "options will exist for each entry in `key-clusters`, a section "
    "documented [here](options-main.md)."]
   [[:matrix-columns]
    {:default [{}]
     :parse-fn vec
     :validate [(spec/coll-of ::valid/column-disposition)]}
    "A list of key columns. Columns are aligned with the user’s fingers. "
    "Each column will be known by its index in this list, starting at zero "
    "for the first item. Each item may contain:\n"
    "\n"
    "- `rows-above-home`: An integer specifying the amount of keys "
    "on the far side of the home row in the column. If "
    "this parameter is omitted, the effective value will be zero.\n"
    "- `rows-below-home`: An integer specifying the amount of keys "
    "on the near side of the home row in the column. If this "
    "parameter is omitted, the effective value will be zero.\n"
    "\n"
    "For example, on a normal QWERTY keyboard, H is on the home row for "
    "purposes of touch typing, and you would probably want to use it as such "
    "here too, even though the matrix in this program has no necessary "
    "relationship with touch typing, nor with the matrix in your MCU firmware "
    "(TMK/QMK etc.). Your H key will then get the coordinates `[0, 0]` as the "
    "home-row key in the far left column on the right-hand side of the "
    "keyboard.\n"
    "\n"
    "In that first column, to continue the QWERTY pattern, you will want "
    "`rows-above-home` set to 1, to make a Y key, or 2 to make a 6-and-^ key, "
    "or 3 to make a function key above the 6-and-^. Your Y key will have the "
    "coordinates `[0, 1]`. Your 6-and-^ key will have the coordinates "
    "`[0, 2]`, etc.\n"
    "\n"
    "Still in that first column, to finish the QWERTY pattern, you will want "
    "`rows-below-home` set to 2, where the two keys below H are N "
    "(coordinates `[0, -1]`) and Space (coordinates `[0, -2]`).\n"
    "\n"
    "The next item in the list will be column 1, with J as `[1, 0]` and so "
    "on. On the left-hand side of a keyboard with `main-body` → `reflect`, "
    "main-body key clusters are mirrored so that `[0, 0]` will be G instead "
    "of H in QWERTY, `[1, 0]` will be F instead of J, and so on."]
   [[:style]
    {:default :standard :parse-fn keyword :validate [::valid/cluster-style]}
    "Cluster layout style. One of:\n\n"
    "- `standard`: Both columns and rows have the same type of curvature "
    "applied in a logically consistent manner.\n"
    "- `orthographic`: Rows are curved somewhat differently. This creates "
    "more space between columns and may prevent key mounts from fusing "
    "together if you have a broad matrix."]
   [[:aliases]
    {:default {}
     :parse-fn (parse/map-of keyword
                              (parse/tuple-of parse/keyword-or-integer))
     :validate [(spec/map-of keyword?
                             (spec/coll-of ::valid/flexcoord :count 2))]}
    "A map of short names to specific keys by coordinate pair. "
    "These names can be used as anchors for other features."]
   [[:anchoring]
    anch/anchoring-metadata
    "Where to place the cluster. "
    stock/anchoring-documentation]])
